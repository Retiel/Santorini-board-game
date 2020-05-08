package it.polimi.ingsw.PSP33.view.cli;

import it.polimi.ingsw.PSP33.events.toClient.data.DataGrid;
import it.polimi.ingsw.PSP33.events.toClient.setup.PossiblePlacement;
import it.polimi.ingsw.PSP33.events.toClient.turn.*;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.view.AbstractView;

/**
 * the rapppresentation of the View Class for the CLI mode
 */
public class CLI extends AbstractView {


    public static void main(String[] args) {
        Board board = new Board();
        CLIPrinter cliPrinter = new CLIPrinter();

        cliPrinter.printBoard(board);

    }

    @Override
    public void visit(DataGrid dataGrid) {
        //setup grid info in the payer's view
    }

    @Override
    public void visit(PossiblePlacement possiblePlacement) {
        //print 2 times the placement for the pawn (setup phase)
    }

    @Override
    public void visit(YouLose youLose) {
        System.out.println("You lose!");
    }

    @Override
    public void visit(YouWin youWin) {
        System.out.println("You win!");
    }

    @Override
    public void visit(NewAction newAction) {

        //print board

        //decide action with the Boolean and send input to controller

    }

    @Override
    public void visit(PossibleBuild possibleBuild) {

    }

    @Override
    public void visit(PossibleMove possibleMove) {

    }

    @Override
    public void visit(PossibleExtraAction possibleExtraAction) {

    }

    /*todo: methods called in the Client class for the implementation of the CLI manager*/
}
