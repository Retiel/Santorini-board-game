package it.polimi.ingsw.PSP33.controller.rules.__implementation;

import it.polimi.ingsw.PSP33.controller.rules.GetCell;
import it.polimi.ingsw.PSP33.controller.rules.BasicAction;
import it.polimi.ingsw.PSP33.controller.rules._build.Build;
import it.polimi.ingsw.PSP33.controller.rules._extraTurn.ExtraAction;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

/**
 * Build with Demeter rules
 *
 */
public class Demeter implements ExtraAction {

    @Override
    public List<Cell> executePlusAction(Pawn pawn, Board board, Cell oldCell) {
        List<Cell> cellList = GetCell.getBuildableCells(pawn, board);
        cellList.remove(oldCell);
        return cellList;
    }

    @Override
    public void applyAction(Cell cell, Pawn pawn, Board board) {
        BasicAction.BuildBlock(cell);
    }
}
