package it.polimi.ingsw.PSP33.controller.rules.gods.strategy.move;

import it.polimi.ingsw.PSP33.controller.rules.tools.BasicAction;
import it.polimi.ingsw.PSP33.events.toClient.turn.NewAction;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.ArrayList;
import java.util.List;


/**
 * Base type of move
 *
 */
public class MoveBasic implements Move {

    @Override
    public List<Cell> checkMove(Pawn pawn, Board board) {
        return new ArrayList<>();
    }

    @Override
    public void executeMove(Cell newCell, Pawn pawn, Model model) {
        Cell oldCell = model.getBoard().getGrid()[pawn.getCoordX()][pawn.getCoordY()];
        BasicAction.MovePawn(oldCell, newCell, pawn);
        model.notifyObservers(new NewAction(false, true, false));
    }
}
