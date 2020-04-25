package it.polimi.ingsw.PSP33.controller.rules.__implementation;

import it.polimi.ingsw.PSP33.controller.rules.GetCell;
import it.polimi.ingsw.PSP33.controller.rules.BasicAction;
import it.polimi.ingsw.PSP33.controller.rules._extraTurn.ExtraAction;
import it.polimi.ingsw.PSP33.controller.rules.buffer_control.DataBuffer;
import it.polimi.ingsw.PSP33.events.toClient.turn.NewAction;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

/**
 * Movement with the rule of Artemis
 *
 */
public class Artemis implements ExtraAction {

    @Override
    public List<Cell> checkPlusAction(Pawn pawn, Board board, DataBuffer dataBuffer) {
        List<Cell> cellList = GetCell.getMovableCells(pawn, board);
        cellList.remove(dataBuffer.getOldPosition());
        return cellList;
    }

    @Override
    public void applyAction(Cell cell, Pawn pawn, Model model) {
        Cell oldCell = model.getBoard().getGrid()[pawn.getCoordX()][pawn.getCoordY()];
        BasicAction.MovePawn(oldCell, cell, pawn);

        model.notifyObservers(new NewAction(false, true, false));
    }
}
