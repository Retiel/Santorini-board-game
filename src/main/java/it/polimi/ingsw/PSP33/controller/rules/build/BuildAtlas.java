package it.polimi.ingsw.PSP33.controller.rules.build;

import it.polimi.ingsw.PSP33.controller.rules.GetCell;
import it.polimi.ingsw.PSP33.controller.rules.BasicAction;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

/**
 * Build with Atlas rules
 *
 */
public class BuildAtlas implements Build {

    @Override
    public List<Cell> checkBuild(Pawn pawn, Board board) {
        return GetCell.getBuildableCells(pawn, board);
    }

    @Override
    public void executeBuild(Cell cellToBuild, boolean trigger) {
        if(trigger){
            cellToBuild.setRoof(true);
        }else{
            BasicAction.BuildBlock(cellToBuild);
        }
    }
}
