package it.polimi.ingsw.PSP33.controller.rules;

import it.polimi.ingsw.PSP33.controller.rules._build.BuildContext;
import it.polimi.ingsw.PSP33.controller.rules._enemyTurn.LimiterContext;
import it.polimi.ingsw.PSP33.controller.rules._move.MoveContext;
import it.polimi.ingsw.PSP33.controller.rules._extraTurn.ExtraContext;
import it.polimi.ingsw.PSP33.controller.rules._win.WinContext;
import it.polimi.ingsw.PSP33.controller.rules.buffer_control.DataBuffer;
import it.polimi.ingsw.PSP33.controller.rules.buffer_control.FlagControl;
import it.polimi.ingsw.PSP33.events.toClient.MVEvent;
import it.polimi.ingsw.PSP33.events.toClient.data.DataModel;
import it.polimi.ingsw.PSP33.events.toClient.turn.NewAction;
import it.polimi.ingsw.PSP33.events.toClient.turn.PossibleBuild;
import it.polimi.ingsw.PSP33.events.toClient.turn.PossibleExtraAction;
import it.polimi.ingsw.PSP33.events.toClient.turn.PossibleMove;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;
import it.polimi.ingsw.PSP33.utils.Coord;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that manage everything related to how execute a player turn
 */
public class TurnFlow {

    private final Model model;
    private final Board board;
    private Pawn pawn;
    private String godName;

    private MoveContext moveContext;
    private BuildContext buildContext;
    private WinContext winContext;
    private ExtraContext extraContext;
    private LimiterContext limiterContext;
    private DataBuffer dataBuffer;

    public TurnFlow(Model model) {
        this.model = model;
        this.board = model.getBoard();
    }

    /**
     * Method that starts every players turn, it reset context and change it to the current
     */
    public void NewTurnContext(){

        this.godName = model.getCurrentPlayer().getCard().getName();
        this.pawn = null;

        this.moveContext = new MoveContext(godName);
        this.buildContext = new BuildContext(godName);
        this.winContext = new WinContext(godName);
        this.extraContext = new ExtraContext(godName);
        this.limiterContext = new LimiterContext();

        model.notifyObservers(new NewAction(true, false, FlagControl.checkStart(godName)));
    }

    /**
     * Method that controls the move choice option
     * @param pawnNumber the pawn involved in the action
     */
    public void MoveFlow(int pawnNumber){

        if (pawn == null) pawn = model.getCurrentPlayer().getPawnByNumber(pawnNumber);

        List<Coord> basicMove = GetCell.getListAdapter(getCellsBasic("move"));
        List<Coord> godsMove = GetCell.getListAdapter(getCellsContext("move"));

        model.notifyObservers(new PossibleMove(basicMove, godsMove));
    }

    /**
     * Method that controls the build choice option
     * @param pawnNumber the pawn involved in the action
     */
    public void BuildFlow(int pawnNumber){

        if (pawn == null) pawn = model.getCurrentPlayer().getPawnByNumber(pawnNumber);

        List<Coord> basicBuild = GetCell.getListAdapter(getCellsBasic("build"));
        List<Coord> godsBuild = GetCell.getListAdapter(getCellsContext("build"));

        model.notifyObservers(new PossibleBuild(basicBuild, godsBuild, FlagControl.checkbuild(godName)));
    }

    /**
     * Method that controls the extra action option, it activates only if there is a extra option dictated by the god card
     * @param pawnNumber the pawn involved in the action
     */
    public void ExtraActionFlow(int pawnNumber){

        if (pawn == null) pawn = model.getCurrentPlayer().getPawnByNumber(pawnNumber);

        List<Cell> cellListBasic = getCellsBasic(FlagControl.checkStringExtra(godName));
        List<Cell> cellListContext = getCellsContext(FlagControl.checkStringExtra(godName));

        List<Coord> basic = GetCell.getListAdapter(cellListBasic);
        List<Coord> gods = GetCell.getListAdapter(cellListContext);

        if(checkList(basic, gods)) model.notifyObservers(new PossibleExtraAction(basic, gods));

    }

    /**
     * Method to apply the move change
     * @param coord coordinates of the new position
     */
    public void ExecMove(Coord coord){
        winContext.checkWinCondition(board, pawn, GetCell.getCellAdapter(coord,board));
        moveContext.execMove(coord.getX(), coord.getY(), pawn, board);
        model.notifyObservers(new DataModel(model));
    }

    /**
     * Method to apply the build change
     * @param coord coordinates of the build position
     */
    public void ExecBuild(Coord coord, boolean roof) {
        buildContext.execBuild(coord.getX(), coord.getY(), board, roof);
        model.notifyObservers(new DataModel(model));
    }

    /**
     * Method to apply the extra action change
     * @param coord coordinates where to apply the extra action
     */
    public void ExecExtra(Coord coord){
        extraContext.ExecAction(coord, pawn, board);
        model.notifyObservers(new DataModel(model));
    }

    /**
     * Method gets the list of cell based on the basic action
     * @param action the action context
     *
     * @return List of Cell class object
     */
    private List<Cell> getCellsBasic(String action){

        switch (action){
            case "move": return GetCell.getMovableCells(pawn, board);
            case "build": return GetCell.getBuildableCells(pawn, board);
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
            case "move": return moveContext.checkMove(pawn, board);
            case "build": return buildContext.checkBuild(pawn, board);
            case "extra": return extraContext.extraRequest(pawn, board, null);
            default:
                return new ArrayList<>();
        }
    }

    /**
     * Method check if the list in input are empty
     * @param coordList1 first list to control
     * @param coordList2 second list to control
     *
     * @return Boolean
     */
    private boolean checkList(List<Coord> coordList1, List<Coord> coordList2){
        return coordList1.isEmpty() && coordList2.isEmpty();
    }

    public void notifyTurn(MVEvent message){
        model.notifyObservers(message);
    }
}
