package it.polimi.ingsw.PSP33.controller.rules.move;

import it.polimi.ingsw.PSP33.controller.rules.Tools;
import it.polimi.ingsw.PSP33.controller.rules.TurnAction;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

/**
 * Movement with the rule of Artemis
 *
 */
public class MoveArtemis implements Move {


    @Override
    public List<Cell> checkMove(Pawn pawn, Board board) {
        return Tools.getMovableCells(pawn, board);
    }

    @Override
    public void executeMove(Cell newCell, Pawn pawn, Board board) {

        Cell oldCell = board.getGrid()[pawn.getCoordX()][pawn.getCoordY()];
        TurnAction.MovePawn(oldCell, newCell, pawn);
    }
}
