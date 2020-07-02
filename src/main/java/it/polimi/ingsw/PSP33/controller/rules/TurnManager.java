package it.polimi.ingsw.PSP33.controller.rules;

import it.polimi.ingsw.PSP33.controller.rules.gods.strategy.build.BuildContext;
import it.polimi.ingsw.PSP33.controller.rules.gods.strategy.enemy.LimiterContext;
import it.polimi.ingsw.PSP33.controller.rules.gods.strategy.extra.ExtraContext;
import it.polimi.ingsw.PSP33.controller.rules.gods.strategy.move.MoveContext;
import it.polimi.ingsw.PSP33.controller.rules.gods.strategy.win.WinContext;
import it.polimi.ingsw.PSP33.controller.rules.tools.*;
import it.polimi.ingsw.PSP33.events.to_client.data.DataCell;
import it.polimi.ingsw.PSP33.events.to_client.setup.PossiblePlacement;
import it.polimi.ingsw.PSP33.events.to_client.setup.SelectGods;
import it.polimi.ingsw.PSP33.events.to_client.setup.YourGod;
import it.polimi.ingsw.PSP33.events.to_client.turn.*;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;
import it.polimi.ingsw.PSP33.model.Player;
import it.polimi.ingsw.PSP33.utils.Coord;
import it.polimi.ingsw.PSP33.utils.enums.Actions;
import it.polimi.ingsw.PSP33.utils.enums.Gods;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that manage everything related to how execute a player turn
 */
public class TurnManager extends AbstractManager {

    private MoveContext moveContext;
    private BuildContext buildContext;
    private WinContext winContext;
    private ExtraContext extraContext;
    private LimiterContext limiterContext;
    private DataBuffer dataBuffer;

    /**
     * Constructor
     * @param model model of the game
     */
    public TurnManager(Model model) {
        super(model);
        this.dataBuffer = new DataBuffer();
        this.limiterContext = new LimiterContext();
    }

    /**
     * Method that starts every players turn, it reset context and change it to the current
     */
    public void newTurnContext(){

        this.dataBuffer = new DataBuffer();
        Gods name = getModel().getCurrentPlayer().getCard().getName();
        getModel().setCurrentGodName(name);

        this.moveContext = new MoveContext(name);
        this.buildContext = new BuildContext(name);
        this.winContext = new WinContext(name);
        this.extraContext = new ExtraContext(name);

        limiterContext.resetGodTrigger(name, DataControl.limitReset(name));
        
        forwardControl();
    }

    /**
     * Method send message ne action
     */
    public void newAction(){
        getModel().getCurrentPawn().setOldMove(null);
        getModel().getCurrentPawn().setOldBuild(null);
        getModel().getCurrentPawn().setOldExtra(null);
        notifyView(new NewAction(true, false, DataControl.checkStart(getModel().getCurrentGodName())));
    }

    /**
     * Method that controls the move choice option
     */
    public void moveFlow(){

        /* NOTE! the list have to be excluse to unless the effect afflict the basic list by shrinking it */
        List<Coord> basicMove = getBasicFlow(Actions.MOVE);
        List<Coord> godsMove = getContextFlow(Actions.MOVE);

        setData(basicMove, godsMove);
        if(DataControl.exclusiveList(basicMove, godsMove)){
            notifyView(new PossibleMove(basicMove, godsMove));
        }
        else {
            // only Prometheus might use this branch
            notifyView(new PossibleMove(godsMove, new ArrayList<>()));
        }
    }

    /**
     * Method that controls the build choice option
     */
    public void buildFlow(){

        List<Coord> basicBuild = getBasicFlow(Actions.BUILD);
        List<Coord> godsBuild = getContextFlow(Actions.BUILD);

        if(noActionAvailable(basicBuild, godsBuild)){
            loserBracket();
        }
        else{
            setData(basicBuild, godsBuild);
            if(DataControl.exclusiveList(basicBuild, godsBuild)){
                notifyView(new PossibleBuild(basicBuild, godsBuild, DataControl.checkBuild(getModel().getCurrentGodName())));
            }
            else {
                notifyView(new PossibleBuild(godsBuild, new ArrayList<>(), DataControl.checkBuild(getModel().getCurrentGodName())));
            }
        }
    }

    /**
     * Method that controls the extra action option, it activates only if there is a extra option dictated by the god card
     */
    public void extraActionFlow(){

        List<Coord> gods = getContextFlow(Actions.EXTRA);

        setData(gods, gods);
        notifyView(new PossibleExtraAction(gods));
    }

    /**
     * Method to apply the move change
     * @param coord coordinates of the new position
     */
    public void execMove(Coord coord){

        if (DataControl.controlInput(coord,dataBuffer)){
            setData(Actions.MOVE, coord);
            if (winContext.checkWinCondition(getBoard(), getModel().getCurrentPawn(), GetCell.getCellAdapter(coord,getBoard()))){
                winningBracket();
            }
            moveContext.execMove(coord.getX(), coord.getY(), getModel().getCurrentPawn(), getModel());
        }
        else notifyView(new PossibleMove(dataBuffer.getCoordList(), dataBuffer.getCoordListGods()));
    }

    /**
     * Method to apply the build change
     * @param coord coordinates of the build position
     * @param roof true if a roof has to be built
     */
    public void execBuild(Coord coord, boolean roof) {

        if (DataControl.controlInput(coord,dataBuffer)) {
            setData(Actions.BUILD, coord);
            buildContext.execBuild(coord.getX(), coord.getY(), roof, getModel());
        }
        else notifyView(new PossibleBuild(dataBuffer.getCoordList(), dataBuffer.getCoordListGods(), DataControl.checkBuild(getModel().getCurrentGodName())));
    }

    /**
     * Method to apply the extra action change
     * @param coord coordinates where to apply the extra action
     * @param toggle enable god effect
     */
    public void execExtra(Coord coord, boolean toggle){

        if (DataControl.controlInput(coord,dataBuffer) || !toggle) {
            if (coord != null){
                setData(Actions.EXTRA, coord);
            }
            extraContext.execAction(coord, getModel().getCurrentPawn(), getModel(), toggle);
        }
        else notifyView(new PossibleExtraAction(dataBuffer.getCoordList()));
    }

    /**
     * Method to reset limiters applied by a god before removing the player
     * @param playerName player to remove
     */
    public void resetLimiters(String playerName){
        Player player = getModel().getPlayers().stream().filter(p -> playerName.equals(p.getName())).findAny().orElse(null);
        if (player != null){
            if(player.getCard() != null){
                Gods name = player.getCard().getName();
                limiterContext.resetGodTrigger(name, DataControl.limitReset(name));
            }
        }
    }

    /**
     * Method to remove a player form the game
     * @param name name of the diconnected player
     */
    public void removePlayer(String name){

        Player player = getModel().getPlayers().stream().filter(p -> name.equals(p.getName())).findAny().orElse(null);

        List<Player> players = getModel().getPlayers();
        if(player != null){
            removePawn(player.getPawns());
            players.remove(player);
        }

        nextTurn();
        getModel().setPlayers(players);

        if (players.size() == 1) {
            notifyView(new YouWin(players.get(0).getName()));
        }
        else if(players.size() > 1){
            boolean flag = true;

            for (Player player1 : players){
                if (player1.getPawnByNumber(1).getCoord() == null || player1.getPawnByNumber(2).getCoord() == null){
                    flag = false;
                    notifyView(new PossiblePlacement(GetCell.getListAdapter(GetCell.getPlaceCells(getBoard()))));
                    break;
                }

                if (player1.getCard() == null){
                    flag = false;
                    if (getGods() == null) notifyView(new SelectGods(getAllgods(),players.size()));
                    else notifyView(new YourGod(getGods()));
                    break;
                }
            }
            if (flag) newTurnContext();
        }

    }

    /**
     * Method to communicate and change the state of the game cause losing player
     */
    private void loserBracket(){

        String player = getModel().getCurrentPlayer().getName();
        notifyView(new YouLose(""));
        nextTurn();
        removePlayer(player);

    }

    /**
     * Method to communicate and change the state of the game cause win condition met
     */
    private void winningBracket(){
        notifyView(new YouWin(getModel().getCurrentPlayer().getName()));
    }

    /**
     * Method gets the list of cell based on the basic action
     * @param action the action context
     *
     * @return List of Cell class object
     */
    private List<Cell> getCellsBasic(Actions action){

        switch (action){
            case MOVE: return GetCell.getMovableCells(getModel().getCurrentPawn(), getBoard());
            case BUILD: return GetCell.getBuildableCells(getModel().getCurrentPawn(), getBoard());
            default:
                return new ArrayList<>();
        }
    }

    /**
     * Method gets the list of cell based on the gods
     * @param action the action context
     *
     * @return List of Cell class object
     */
    private List<Cell> getCellsContext(Actions action){
        switch (action){
            case MOVE: return moveContext.checkMove(getModel().getCurrentPawn(), getBoard());
            case BUILD: return buildContext.checkBuild(getModel().getCurrentPawn(), getBoard());
            case EXTRA: return extraContext.extraRequest(getModel().getCurrentPawn(), getBoard());
            default:
                return new ArrayList<>();
        }
    }

    /**
     * Method check if the list in input are empty (Losing condition)
     * @param coordList1 first list to control
     * @param coordList2 second list to control
     *
     * @return Boolean
     */
    private boolean noActionAvailable(List<Coord> coordList1, List<Coord> coordList2){
        return coordList1.isEmpty() && coordList2.isEmpty();
    }

    /**
     * Method to control the mobility of the pawns of the current player
     */
    private void forwardControl(){

        Pawn pawn1 = getModel().getCurrentPlayer().getPawnByNumber(1);
        Pawn pawn2 = getModel().getCurrentPlayer().getPawnByNumber(2);

        // verify pawn 1
        getModel().setCurrentPawn(pawn1);

        List<Coord> move1 = getBasicFlow(Actions.MOVE);
        List<Coord> g_move1 =  getContextFlow(Actions.MOVE);

        // verify pawn 2
        getModel().setCurrentPawn(pawn2);

        List<Coord> move2 = getBasicFlow(Actions.MOVE);
        List<Coord> g_move2 =  getContextFlow(Actions.MOVE);

        // test case
        boolean test_move1 = noActionAvailable(move1, g_move1);
        boolean test_move2 = noActionAvailable(move2, g_move2);

        if (test_move1){
            if (test_move2){
                loserBracket();
            }else {
                notifyView(new SelectPawn(2));
            }
        }else {
            if (test_move2){
                notifyView(new SelectPawn(1));
            }else {
                notifyView(new SelectPawn(0));
            }
        }
    }

    /**
     * Method filter list based on gods effect
     * @param cellList list to filter
     *
     * @return List of Cell object
     */
    private List<Cell> applyLimit(List<Cell> cellList){
        return limiterContext.applyAllLimit(cellList, getModel().getCurrentPawn(), getBoard(), getModel().getCurrentGodName());
    }

    /**
     * Method to get list of coord for the type of action under basic rule
     * @param action type of action
     *
     * @return List of Coord object
     */
    private List<Coord> getBasicFlow(Actions action){
        if(action == Actions.BUILD) return GetCell.getListAdapter(getCellsBasic(action));
        else return GetCell.getListAdapter(applyLimit(getCellsBasic(action)));
    }

    /**
     * Method to get list of coord for the type of action with context
     * @param action type of action
     *
     * @return List of Coord object
     */
    private List<Coord> getContextFlow(Actions action){
        if(action == Actions.BUILD) return GetCell.getListAdapter(getCellsContext(action));
        else return GetCell.getListAdapter(applyLimit(getCellsContext(action)));
    }

    /**
     * Method to update old data in the model
     * @param action type of action
     * @param coord coordinate related
     */
    private void setData(Actions action, Coord coord){

        Cell cell;

        switch (action){
            case MOVE:
                cell = GetCell.getCellAdapter(getModel().getCurrentPawn().getCoord(), getBoard());
                getModel().getCurrentPawn().setOldMove(cell.getCoord());
                break;
            case BUILD:
                cell = GetCell.getCellAdapter(coord, getBoard());
                getModel().getCurrentPawn().setOldBuild(cell.getCoord());
                break;
            case EXTRA:
                cell = GetCell.getCellAdapter(coord, getBoard());
                getModel().getCurrentPawn().setOldExtra(cell.getCoord());
                break;
        }
        
        limiterContext.activateGodLimiter(getModel().getCurrentGodName(), getModel().getCurrentPawn(), coord, getBoard());
    }

    /**
     * Method to update data buffer
     * @param basicList list of coordinates related to basic action
     * @param godList list of coordinates related to gods effect
     */
    private void setData(List<Coord> basicList, List<Coord> godList){
        dataBuffer.setCoordList(basicList);
        dataBuffer.setCoordListGods(godList);
    }

    /**
     * Method to remove pawns of the removed player from the grid
     * @param pawns list of the pawns
     */
    private void removePawn(Pawn[] pawns){
        for (Pawn pawn: pawns){
            if(pawn.getCoord() != null){
                Coord coord = pawn.getCoord();

                Cell cell = getBoard().getGrid()[coord.getX()][coord.getY()];
                cell.setOccupied(null);
                notifyView(new DataCell(LightConversion.getLightVersion(cell), null));
            }
        }
    }

}
