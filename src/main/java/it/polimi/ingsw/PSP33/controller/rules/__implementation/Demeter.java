package it.polimi.ingsw.PSP33.controller.rules.__implementation;

import it.polimi.ingsw.PSP33.controller.rules.GetCell;
import it.polimi.ingsw.PSP33.controller.rules.BasicAction;
import it.polimi.ingsw.PSP33.controller.rules._build.Build;
import it.polimi.ingsw.PSP33.controller.rules._extraTurn.ExtraAction;
import it.polimi.ingsw.PSP33.controller.rules.buffer_control.DataBuffer;
import it.polimi.ingsw.PSP33.events.toClient.turn.NewAction;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

/**
 * Extra action with Demeter rules
 *
 */
public class Demeter implements Build,  ExtraAction {

    @Override
    public List<Cell> checkPlusAction(Pawn pawn, Board board, DataBuffer dataBuffer) {
        List<Cell> cellList = GetCell.getBuildableCells(pawn, board);
        cellList.remove(dataBuffer.getOldBuild());
        return cellList;
    }

    @Override
    public void applyAction(Cell cell, Pawn pawn, Model model) {
        BasicAction.BuildBlock(cell);

        model.notifyObservers(new NewAction(false, false, false));
    }

    @Override
    public List<Cell> checkBuild(Pawn pawn, Board board) {
        return GetCell.getBuildableCells(pawn, board);
    }

    @Override
    public void executeBuild(Cell cellToBuild, boolean trigger, Model model) {
        BasicAction.BuildBlock(cellToBuild);
        model.notifyObservers(new NewAction(false, false, true));
    }
}
