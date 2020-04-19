package it.polimi.ingsw.PSP33.controller.rules._implementation;

import it.polimi.ingsw.PSP33.controller.rules.GetCell;
import it.polimi.ingsw.PSP33.controller.rules.BasicAction;
import it.polimi.ingsw.PSP33.controller.rules.move.Move;
import it.polimi.ingsw.PSP33.controller.rules.turn.ExtraAction;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

/**
 * Movement with the rule of Artemis
 *
 */
public class Artemis implements Move, ExtraAction {

    private Cell previousPosition;

    @Override
    public List<Cell> checkMove(Pawn pawn, Board board) {
        List<Cell> cellList = GetCell.getMovableCells(pawn, board);
        if(previousPosition != null) cellList.remove(previousPosition);
        return cellList;
    }

    @Override
    public void executeMove(Cell newCell, Pawn pawn, Board board) {

        Cell oldCell = board.getGrid()[pawn.getCoordX()][pawn.getCoordY()];
        if (previousPosition == null) previousPosition = oldCell;
        BasicAction.MovePawn(oldCell, newCell, pawn);
    }

    @Override
    public List<Cell> executePlusAction(Pawn pawn, Board board) {
        return checkMove(pawn, board);
    }

    @Override
    public void applyAction(Cell cell, Pawn pawn, Board board) {
        executeMove(cell, pawn, board);
    }
}
