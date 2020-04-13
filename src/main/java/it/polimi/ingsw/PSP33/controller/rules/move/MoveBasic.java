package it.polimi.ingsw.PSP33.controller.rules.move;

import it.polimi.ingsw.PSP33.controller.rules.GetCell;
import it.polimi.ingsw.PSP33.controller.rules.BasicAction;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;


/**
 * Base type of move
 *
 */
public class MoveBasic implements Move {

    @Override
    public List<Cell> checkMove(Pawn pawn, Board board) {
        return GetCell.getMovableCells(pawn, board);
    }

    @Override
    public void executeMove(Cell newCell, Pawn pawn, Board board) {
        Cell oldCell = board.getGrid()[pawn.getCoordX()][pawn.getCoordY()];
        BasicAction.MovePawn(oldCell, newCell, pawn);
    }
}
