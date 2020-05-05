package it.polimi.ingsw.PSP33.controller.rules.gods;

import it.polimi.ingsw.PSP33.controller.rules.tools.GetCell;
import it.polimi.ingsw.PSP33.controller.rules.tools.BasicAction;
import it.polimi.ingsw.PSP33.controller.rules.gods.strategy.extra.ExtraAction;
import it.polimi.ingsw.PSP33.controller.rules.gods.strategy.move.Move;
import it.polimi.ingsw.PSP33.events.toClient.turn.NewAction;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

/**
 * Extra action with the rule of Artemis
 *
 */
public class Artemis implements Move, ExtraAction {

    @Override
    public List<Cell> checkPlusAction(Pawn pawn, Board board) {
        List<Cell> cellList = GetCell.getMovableCells(pawn, board);
        cellList.remove(GetCell.getCellAdapter(pawn.getOldMove(), board));
        return cellList;
    }

    @Override
    public void applyAction(Cell cell, Pawn pawn, Model model) {
        Cell oldCell = model.getBoard().getGrid()[pawn.getCoordX()][pawn.getCoordY()];
        BasicAction.MovePawn(oldCell, cell, pawn);

        model.notifyObservers(new NewAction(false, true, false));
    }

    @Override
    public List<Cell> checkMove(Pawn pawn, Board board) {
        return GetCell.getMovableCells(pawn, board);
    }

    @Override
    public void executeMove(Cell newCell, Pawn pawn, Model model) {

        Cell oldCell = model.getBoard().getGrid()[pawn.getCoordX()][pawn.getCoordY()];
        BasicAction.MovePawn(oldCell, newCell, pawn);

        model.notifyObservers(new NewAction(false, true, true));
    }
}
