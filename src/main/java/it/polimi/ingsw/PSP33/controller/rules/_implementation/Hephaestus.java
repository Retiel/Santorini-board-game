package it.polimi.ingsw.PSP33.controller.rules._implementation;

import it.polimi.ingsw.PSP33.controller.rules.GetCell;
import it.polimi.ingsw.PSP33.controller.rules.BasicAction;
import it.polimi.ingsw.PSP33.controller.rules.build.Build;
import it.polimi.ingsw.PSP33.controller.rules.turn.ExtraAction;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.ArrayList;
import java.util.List;

/**
 * Build with Hephaestus rules
 *
 */
public class Hephaestus implements Build, ExtraAction {

    private Cell oldCell = null;

    @Override
    public List<Cell> checkBuild(Pawn pawn, Board board) {

        List<Cell> cellList = new ArrayList<>();
        cellList.add(oldCell);

        if(oldCell != null) return cellList;
        return GetCell.getBuildableCells(pawn, board);
    }

    @Override
    public void executeBuild(Cell cellToBuild, boolean trigger) {
        if (oldCell == null) oldCell = cellToBuild;
        BasicAction.BuildBlock(cellToBuild);
    }

    @Override
    public List<Cell> executePlusAction(Pawn pawn, Board board) {
        return checkBuild(pawn, board);
    }

    @Override
    public void applyAction(Cell cell, Pawn pawn, Board board) {
        executeBuild(cell, false);
    }
}
