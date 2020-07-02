package it.polimi.ingsw.PSP33.controller.rules.gods;

import it.polimi.ingsw.PSP33.controller.rules.gods.strategy.build.Build;
import it.polimi.ingsw.PSP33.controller.rules.gods.strategy.extra.ExtraAction;
import it.polimi.ingsw.PSP33.controller.rules.tools.BasicAction;
import it.polimi.ingsw.PSP33.controller.rules.tools.GetCell;
import it.polimi.ingsw.PSP33.controller.rules.tools.LightConversion;
import it.polimi.ingsw.PSP33.events.to_client.data.DataCell;
import it.polimi.ingsw.PSP33.events.to_client.turn.NewAction;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;
import it.polimi.ingsw.PSP33.model.light_version.LightCell;

import java.util.List;

/**
 * Extra action with Demeter rules
 *
 */
public class Demeter implements Build, ExtraAction {

    @Override
    public List<Cell> checkPlusAction(Pawn pawn, Board board) {
        List<Cell> cellList = GetCell.getBuildableCells(pawn, board);
        cellList.remove(GetCell.getCellAdapter(pawn.getOldBuild(),board));
        return cellList;
    }

    @Override
    public void applyAction(Cell cell, Pawn pawn, Model model, boolean trigger) {
        if (trigger){
            BasicAction.buildBlock(cell);

            LightCell lightCellNew = LightConversion.getLightVersion(cell);

            model.notifyObservers(new DataCell(lightCellNew, null));
        }
        model.notifyObservers(new NewAction(false, false, false));
    }

    @Override
    public List<Cell> checkBuild(Pawn pawn, Board board) {
        return GetCell.getBuildableCells(pawn, board);
    }

    @Override
    public void executeBuild(Cell cellToBuild, boolean trigger, Model model) {
        BasicAction.buildBlock(cellToBuild);

        LightCell lightCellNew = LightConversion.getLightVersion(cellToBuild);

        model.notifyObservers(new DataCell(lightCellNew, null));

        model.notifyObservers(new NewAction(false, false, true));
    }
}
