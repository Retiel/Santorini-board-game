package it.polimi.ingsw.PSP33.controller.rules;

import it.polimi.ingsw.PSP33.controller.rules.gods.strategy.build.BuildContext;
import it.polimi.ingsw.PSP33.controller.rules.gods.strategy.enemy.LimiterContext;
import it.polimi.ingsw.PSP33.controller.rules.gods.strategy.move.MoveContext;
import it.polimi.ingsw.PSP33.controller.rules.gods.strategy.extra.ExtraContext;
import it.polimi.ingsw.PSP33.controller.rules.gods.strategy.win.WinContext;
import it.polimi.ingsw.PSP33.controller.rules.tools.DataBuffer;
import it.polimi.ingsw.PSP33.controller.rules.tools.DataControl;
import it.polimi.ingsw.PSP33.controller.rules.tools.AbstractManager;
import it.polimi.ingsw.PSP33.controller.rules.tools.GetCell;
import it.polimi.ingsw.PSP33.events.toClient.turn.*;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
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
        
        getModel().notifyObservers(new NewAction(
                true, false, DataControl.checkStart(getModel().getCurrentGodName())));
    }

    /**
     * Method that controls the move choice option
     */
    public void moveFlow(){

        /* NOTE! the list have to be excluse to unless the effect afflict the basic list by shrinking it */
        List<Coord> basicMove = getBasicFlow(Actions.MOVE);
        List<Coord> godsMove = getContextFlow(Actions.MOVE);

        if (noActionAvailable(basicMove, godsMove)){

            setData(basicMove, godsMove);
            if(DataControl.exclusiveList(basicMove, godsMove)){
                getModel().notifyObservers(new PossibleMove(basicMove, godsMove));
            }
            else {
                // only Prometheus might use this branch
                getModel().notifyObservers(new PossibleMove(godsMove, new ArrayList<>()));
            }
        }else loserBracket();
    }

    /**
     * Method that controls the build choice option
     */
    public void buildFlow(){

        List<Coord> basicBuild = getBasicFlow(Actions.BUILD);
        List<Coord> godsBuild = getContextFlow(Actions.BUILD);


        if (noActionAvailable(basicBuild, godsBuild)){

            setData(basicBuild, godsBuild);
            if(DataControl.exclusiveList(basicBuild, godsBuild)){
                getModel().notifyObservers(new PossibleBuild(
                        basicBuild, godsBuild, DataControl.checkBuild(getModel().getCurrentGodName())));
            }
            else {
                getModel().notifyObservers(new PossibleBuild(
                        godsBuild, new ArrayList<>(), DataControl.checkBuild(getModel().getCurrentGodName())));
            }
        }else loserBracket();
    }

    /**
     * Method that controls the extra action option, it activates only if there is a extra option dictated by the god card
     */
    public void extraActionFlow(){

        List<Coord> gods = getContextFlow(Actions.EXTRA);

        setData(gods, gods);
        getModel().notifyObservers(new PossibleExtraAction(gods));
    }

    /**
     * Method to apply the move change
     * @param coord coordinates of the new position
     */
    public void execMove(Coord coord){

        if (DataControl.controlInput(coord,dataBuffer)){
            setData(Actions.MOVE, coord);
            if (winContext.checkWinCondition(getBoard(), getModel().getCurrentPawn(), GetCell.getCellAdapter(coord,getBoard()))) winningBracket();
            moveContext.execMove(coord.getX(), coord.getY(), getModel().getCurrentPawn(), getModel());
        }
        else getModel().notifyObservers(new PossibleMove(dataBuffer.getCoordList(), dataBuffer.getCoordListGods()));
    }

    /**
     * Method to apply the build change
     * @param coord coordinates of the build position
     */
    public void execBuild(Coord coord, boolean roof) {

        if (DataControl.controlInput(coord,dataBuffer)) {
            setData(Actions.BUILD, coord);
            buildContext.execBuild(coord.getX(), coord.getY(), roof, getModel());
        }
        else getModel().notifyObservers(new PossibleBuild(
                dataBuffer.getCoordList(), dataBuffer.getCoordListGods(), DataControl.checkBuild(getModel().getCurrentGodName())));
    }

    /**
     * Method to apply the extra action change
     * @param coord coordinates where to apply the extra action
     */
    public void execExtra(Coord coord){

        if (DataControl.controlInput(coord,dataBuffer)) {
            setData(Actions.EXTRA, coord);
            extraContext.ExecAction(coord, getModel().getCurrentPawn(), getModel());
        }
        else getModel().notifyObservers(new PossibleExtraAction(dataBuffer.getCoordList()));
    }

    /**
     * Method to reset limiters applied by a god before removing the player
     * @param playerName player to remove
     */
    public void resetLimiters(String playerName){
        Player player = getModel().getPlayers().stream().filter(p -> playerName.equals(p.getName())).findAny().orElse(null);
        if (player != null){
            Gods name = player.getCard().getName();
            limiterContext.resetGodTrigger(name, DataControl.limitReset(name));
        }
    }

    /**
     * Method to comunicate and chage the state of the game cause losing player
     */
    private void loserBracket(){

        getModel().notifyObservers(new YouLose());
        nextTurn();
        removePlayer(getModel().getCurrentPlayer().getName());

        if (getModel().getPlayers().size() == 1) {
            getModel().notifyObservers(new YouWin());
        }else{
            newTurnContext();
        }
    }

    /**
     * Method to comunicate and chage the state of the game cause win condition met
     */
    private void winningBracket(){
      getModel().notifyObservers(new YouWin());
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
        return !coordList1.isEmpty() || !coordList2.isEmpty();
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
        return GetCell.getListAdapter(applyLimit(getCellsBasic(action)));
    }

    /**
     * Method to get list of coord for the type of action with context
     * @param action type of action
     *
     * @return List of Coord object
     */
    private List<Coord> getContextFlow(Actions action){
        return GetCell.getListAdapter(applyLimit(getCellsContext(action)));
    }

    /**
     * Method to update data buffer
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
}
