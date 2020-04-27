package it.polimi.ingsw.PSP33.controller.rules;

import it.polimi.ingsw.PSP33.controller.rules._build.BuildContext;
import it.polimi.ingsw.PSP33.controller.rules._enemyTurn.LimiterContext;
import it.polimi.ingsw.PSP33.controller.rules._move.MoveContext;
import it.polimi.ingsw.PSP33.controller.rules._extraTurn.ExtraContext;
import it.polimi.ingsw.PSP33.controller.rules._win.WinContext;
import it.polimi.ingsw.PSP33.controller.rules.buffer_control.DataBuffer;
import it.polimi.ingsw.PSP33.controller.rules.buffer_control.DataControl;
import it.polimi.ingsw.PSP33.events.toClient.data.DataModel;
import it.polimi.ingsw.PSP33.events.toClient.turn.*;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Player;
import it.polimi.ingsw.PSP33.utils.Coord;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that manage everything related to how execute a player turn
 */
public class TurnFlow {

    private final Model model;
    private final Board board;

    private MoveContext moveContext;
    private BuildContext buildContext;
    private WinContext winContext;
    private ExtraContext extraContext;
    private LimiterContext limiterContext;
    private DataBuffer dataBuffer;

    /**
     * Constructor
     */
    public TurnFlow(Model model) {
        this.model = model;
        this.board = model.getBoard();
        this.dataBuffer = new DataBuffer();
        this.limiterContext = new LimiterContext();
    }

    /**
     * Method that starts every players turn, it reset context and change it to the current
     */
    public void newTurnContext(){

        this.dataBuffer = new DataBuffer();
        String name = model.getCurrentPlayer().getCard().getName();
        dataBuffer.setGodName(name);

        this.moveContext = new MoveContext(name);
        this.buildContext = new BuildContext(name);
        this.winContext = new WinContext(name);
        this.extraContext = new ExtraContext(name);

        limiterContext.resetGodTrigger(name, DataControl.limitReset(name));
        
        model.notifyObservers(new NewAction(true, false, DataControl.checkStart(dataBuffer.getGodName())));
    }

    /**
     * Method that controls the move choice option
     * @param pawnNumber the pawn involved in the action
     */
    public void moveFlow(int pawnNumber){

        if (dataBuffer.getCurrentPawn() == null) dataBuffer.setCurrentPawn(model.getCurrentPlayer().getPawnByNumber(pawnNumber));

        /* NOTE! the list have to be excluse to unless the effect afflict the basic list by shrinking it */
        List<Coord> basicMove = getBasicFlow("move");
        List<Coord> godsMove = getContextFlow("move");

        if (noActionAvailable(basicMove, godsMove)){

            setData(basicMove, godsMove);
            if(DataControl.exclusiveList(basicMove, godsMove)){
                model.notifyObservers(new PossibleMove(basicMove, godsMove));
            }
            else {
                model.notifyObservers(new PossibleMove(godsMove, new ArrayList<>())); // only Prometheus might use this branch
            }
        }else loserBracket();
    }

    /**
     * Method that controls the build choice option
     * @param pawnNumber the pawn involved in the action
     */
    public void buildFlow(int pawnNumber){

        if (dataBuffer.getCurrentPawn() == null) dataBuffer.setCurrentPawn(model.getCurrentPlayer().getPawnByNumber(pawnNumber));

        List<Coord> basicBuild = getBasicFlow("build");
        List<Coord> godsBuild = getContextFlow("build");


        if (noActionAvailable(basicBuild, godsBuild)){

            setData(basicBuild, godsBuild);
            if(DataControl.exclusiveList(basicBuild, godsBuild)){
                model.notifyObservers(new PossibleBuild(basicBuild, godsBuild, DataControl.checkBuild(dataBuffer.getGodName())));
            }
            else {
                model.notifyObservers(new PossibleBuild(godsBuild, new ArrayList<>(), DataControl.checkBuild(dataBuffer.getGodName())));
            }
        }else loserBracket();
    }

    /**
     * Method that controls the extra action option, it activates only if there is a extra option dictated by the god card
     * @param pawnNumber the pawn involved in the action
     */
    public void extraActionFlow(int pawnNumber){

        if (dataBuffer.getCurrentPawn() == null) dataBuffer.setCurrentPawn(model.getCurrentPlayer().getPawnByNumber(pawnNumber));

        List<Coord> gods = getContextFlow("extra");

        setData(gods, gods);
        model.notifyObservers(new PossibleExtraAction(gods));
    }

    /**
     * Method to apply the move change
     * @param coord coordinates of the new position
     */
    public void execMove(Coord coord){

        if (DataControl.controlInput(coord,dataBuffer)){
            setData("move", coord);
            winContext.checkWinCondition(board, dataBuffer.getCurrentPawn(), GetCell.getCellAdapter(coord,board));
            moveContext.execMove(coord.getX(), coord.getY(), dataBuffer.getCurrentPawn(), model);
            model.notifyObservers(new DataModel(model));
        }
        else model.notifyObservers(new PossibleMove(dataBuffer.getCoordList(), dataBuffer.getCoordListGods()));
    }

    /**
     * Method to apply the build change
     * @param coord coordinates of the build position
     */
    public void execBuild(Coord coord, boolean roof) {

        if (DataControl.controlInput(coord,dataBuffer)) {
            setData("build", coord);
            buildContext.execBuild(coord.getX(), coord.getY(), roof, model);
            model.notifyObservers(new DataModel(model));
        }
        else model.notifyObservers(new PossibleBuild(dataBuffer.getCoordList(), dataBuffer.getCoordListGods(), DataControl.checkBuild(dataBuffer.getGodName())));
    }

    /**
     * Method to apply the extra action change
     * @param coord coordinates where to apply the extra action
     */
    public void execExtra(Coord coord){

        if (DataControl.controlInput(coord,dataBuffer)) {
            setData("extra", coord);
            extraContext.ExecAction(coord, dataBuffer.getCurrentPawn(), model);
            model.notifyObservers(new DataModel(model));
        }
        else model.notifyObservers(new PossibleExtraAction(dataBuffer.getCoordList()));
    }

    /**
     * Method to comunicate and chage the state of the game cause losing player
     */
    private void loserBracket(){
        model.notifyObservers(new YouLose());

        List<Player> players = model.getPlayers();
        players.remove(model.getCurrentPlayer());

        if (players.size() > 1) model.setPlayers(players);
        else {
            //TODO: /* Missing default win definition */
        }
    }

    /**
     * Method to comunicate and chage the state of the game cause win condition met
     */
    private void winningBracket(){
      //TODO:  /* Missing implementation */
    }

    /**
     * Method gets the list of cell based on the basic action
     * @param action the action context
     *
     * @return List of Cell class object
     */
    private List<Cell> getCellsBasic(String action){

        switch (action){
            case "move": return GetCell.getMovableCells(dataBuffer.getCurrentPawn(), board);
            case "build": return GetCell.getBuildableCells(dataBuffer.getCurrentPawn(), board);
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
    private List<Cell> getCellsContext(String action){
        switch (action){
            case "move": return moveContext.checkMove(dataBuffer.getCurrentPawn(), board);
            case "build": return buildContext.checkBuild(dataBuffer.getCurrentPawn(), board);
            case "extra": return extraContext.extraRequest(dataBuffer.getCurrentPawn(), board, dataBuffer);
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
        return limiterContext.applyAllLimit(cellList, dataBuffer.getCurrentPawn(), board, dataBuffer.getGodName());
    }

    /**
     * Method to get list of coord for the type of action under basic rule
     * @param typeFlow typer of action
     *
     * @return List of Coord object
     */
    private List<Coord> getBasicFlow(String typeFlow){
        return GetCell.getListAdapter(applyLimit(getCellsBasic(typeFlow)));
    }

    /**
     * Method to get list of coord for the type of action with context
     * @param typeFlow type of action
     *
     * @return List of Coord object
     */
    private List<Coord> getContextFlow(String typeFlow){
        return GetCell.getListAdapter(applyLimit(getCellsContext(typeFlow)));
    }

    /**
     * Method to update data buffer
     * @param action type of action
     * @param coord coordinate related
     */
    private void setData(String action, Coord coord){

        Cell cell;

        switch (action){
            case "move":
                cell = GetCell.getCellAdapter(dataBuffer.getCurrentPawn().getCoord(), board);
                dataBuffer.setOldPosition(cell);
                break;
            case "build":
                cell = GetCell.getCellAdapter(coord, board);
                dataBuffer.setOldBuild(cell);
                break;
            case "extra":
                cell = GetCell.getCellAdapter(coord, board);
                dataBuffer.setOldExtra(cell);
                break;
        }
        
        limiterContext.activateGodLimiter(dataBuffer.getGodName(), dataBuffer.getCurrentPawn(), coord, board);
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
