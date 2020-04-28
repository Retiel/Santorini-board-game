package it.polimi.ingsw.PSP33.controller.rules.gods;

import it.polimi.ingsw.PSP33.controller.rules.tools.GetCell;
import it.polimi.ingsw.PSP33.controller.rules.tools.BasicAction;
import it.polimi.ingsw.PSP33.controller.rules.gods.strategy.build.Build;
import it.polimi.ingsw.PSP33.events.toClient.turn.NewAction;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

/**
 * Build with Atlas rules
 *
 */
public class Atlas implements Build {

    @Override
    public List<Cell> checkBuild(Pawn pawn, Board board) {
        return GetCell.getBuildableCells(pawn, board);
    }

    @Override
    public void executeBuild(Cell cellToBuild, boolean trigger, Model model) {
        if(trigger){
            cellToBuild.setRoof(true);
        }else{
            BasicAction.BuildBlock(cellToBuild);
        }

        model.notifyObservers(new NewAction(false, false, false));
    }
}
