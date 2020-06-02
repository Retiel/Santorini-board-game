package it.polimi.ingsw.PSP33.controller.rules.gods.strategy.move;

import it.polimi.ingsw.PSP33.controller.rules.tools.BasicAction;
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

        LightCell lightCellNew = LightConvertion.getLightVersion(newCell);
        LightCell lightCellOld = LightConvertion.getLightVersion(oldCell);

        model.notifyObservers(new DataCell(lightCellNew, lightCellOld));
        model.notifyObservers(new NewAction(false, true, false, false));
    }
}
