package it.polimi.ingsw.PSP33.controller.rules.gods.strategy.build;

import it.polimi.ingsw.PSP33.controller.rules.tools.BasicAction;
import it.polimi.ingsw.PSP33.controller.rules.tools.LightConversion;
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
 * Build basic without gods effect
 * (also usable as a template to copy/paste)
 */
public class BuildBasic implements Build {

    @Override
    public List<Cell> checkBuild(Pawn pawn, Board board) {
        return new ArrayList<>();
    }

    @Override
    public void executeBuild(Cell cellToBuild, boolean trigger, Model model) {
        BasicAction.buildBlock(cellToBuild);

        LightCell lightCellNew = LightConversion.getLightVersion(cellToBuild);

        model.notifyObservers(new DataCell(lightCellNew, null));
        model.notifyObservers(new NewAction(false, false, false, false));
    }
}
