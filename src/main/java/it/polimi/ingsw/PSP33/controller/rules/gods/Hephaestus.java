package it.polimi.ingsw.PSP33.controller.rules.gods;

import it.polimi.ingsw.PSP33.controller.rules.tools.BasicAction;
import it.polimi.ingsw.PSP33.controller.rules.tools.GetCell;
import it.polimi.ingsw.PSP33.controller.rules.gods.strategy.build.Build;
import it.polimi.ingsw.PSP33.controller.rules.gods.strategy.extra.ExtraAction;
import it.polimi.ingsw.PSP33.controller.rules.tools.LightConvertion;
import it.polimi.ingsw.PSP33.events.toClient.data.DataCell;
import it.polimi.ingsw.PSP33.events.toClient.turn.NewAction;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;
import it.polimi.ingsw.PSP33.model.light_version.LightCell;

import java.util.ArrayList;
import java.util.List;

/**
 * Extra action with Hephaestus rules
 *
 */
public class Hephaestus implements Build, ExtraAction {

    @Override
    public List<Cell> checkPlusAction(Pawn pawn, Board board) {
        List<Cell> cellList = new ArrayList<>();
        if(GetCell.getCellAdapter(pawn.getOldBuild(), board).getFloor() < 3){
            cellList.add(GetCell.getCellAdapter(pawn.getOldBuild(), board));
        }
        return cellList;
    }

    @Override
    public void applyAction(Cell cell, Pawn pawn, Model model) {
        BasicAction.BuildBlock(cell);

        LightCell lightCellNew = LightConvertion.getLightVersion(cell);

        model.notifyObservers(new DataCell(lightCellNew, null));
        model.notifyObservers(new NewAction(false, false, false));

    }

    @Override
    public List<Cell> checkBuild(Pawn pawn, Board board) {
        return GetCell.getBuildableCells(pawn, board);
    }

    @Override
    public void executeBuild(Cell cellToBuild, boolean trigger, Model model) {
        BasicAction.BuildBlock(cellToBuild);

        LightCell lightCellNew = LightConvertion.getLightVersion(cellToBuild);

        model.notifyObservers(new DataCell(lightCellNew, null));
        model.notifyObservers(new NewAction(false, false, true));
    }
}
