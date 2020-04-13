package it.polimi.ingsw.PSP33.controller.rules.build;

import it.polimi.ingsw.PSP33.controller.rules.GetCell;
import it.polimi.ingsw.PSP33.controller.rules.BasicAction;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.ArrayList;
import java.util.List;

/**
 * Build with Hephaestus rules
 *
 */
public class BuildHephaestus implements Build {

    private Cell oldCell;

    @Override
    public List<Cell> checkBuild(Pawn pawn, Board board) {

        List<Cell> cellList = new ArrayList<>();

        if(oldCell == null){
            return GetCell.getBuildableCells(pawn, board);
        }

        cellList.add(oldCell);
        return cellList;
    }

    @Override
    public void executeBuild(Cell cellToBuild, boolean trigger) {
        if (trigger) oldCell = cellToBuild;
        else oldCell = null;
        BasicAction.BuildBlock(cellToBuild);
    }
}
