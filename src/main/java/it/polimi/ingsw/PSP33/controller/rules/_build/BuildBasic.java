package it.polimi.ingsw.PSP33.controller.rules._build;

import it.polimi.ingsw.PSP33.controller.rules.BasicAction;
import it.polimi.ingsw.PSP33.events.toClient.turn.NewAction;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.ArrayList;
import java.util.List;

/**
 * Build basic without gods effect
 *
 */
public class BuildBasic implements Build {

    @Override
    public List<Cell> checkBuild(Pawn pawn, Board board) {
        return new ArrayList<>();
    }

    @Override
    public void executeBuild(Cell cellToBuild, boolean trigger, Model model) {
        BasicAction.BuildBlock(cellToBuild);
        model.notifyObservers(new NewAction(false, false, false));
    }
}
