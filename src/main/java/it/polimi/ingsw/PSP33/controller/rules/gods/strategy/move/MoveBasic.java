package it.polimi.ingsw.PSP33.controller.rules.gods.strategy.move;

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
 * Move basic without gods effect
 * (also usable as a template to copy/paste)
 */
public class MoveBasic implements Move {

    @Override
    public List<Cell> checkMove(Pawn pawn, Board board) {
        return new ArrayList<>();
    }

    @Override
    public void executeMove(Cell newCell, Pawn pawn, Model model) {
        Cell oldCell = model.getBoard().getGrid()[pawn.getCoordX()][pawn.getCoordY()];
        BasicAction.movePawn(oldCell, newCell, pawn);

        LightCell lightCellNew = LightConversion.getLightVersion(newCell);
        LightCell lightCellOld = LightConversion.getLightVersion(oldCell);

        model.notifyObservers(new DataCell(lightCellNew, lightCellOld));
        model.notifyObservers(new NewAction(false, true, false, false));
    }
}
