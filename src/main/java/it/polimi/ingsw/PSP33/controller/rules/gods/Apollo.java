package it.polimi.ingsw.PSP33.controller.rules.gods;

import it.polimi.ingsw.PSP33.controller.rules.tools.GetCell;
import it.polimi.ingsw.PSP33.controller.rules.tools.BasicAction;
import it.polimi.ingsw.PSP33.controller.rules.gods.strategy.move.Move;
import it.polimi.ingsw.PSP33.controller.rules.tools.LightConvertion;
import it.polimi.ingsw.PSP33.events.toClient.data.DataCell;
import it.polimi.ingsw.PSP33.events.toClient.turn.NewAction;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;
import it.polimi.ingsw.PSP33.model.light_version.LightCell;
import it.polimi.ingsw.PSP33.model.light_version.LightPawn;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Movement with the rule of Apollo
 *
 */
public class Apollo implements Move {

    @Override
    public List<Cell> checkMove(Pawn pawn, Board board) {

        List<Cell> movableCells = GetCell.getMovableCells(pawn, board);
        List<Cell> adjacentCells = GetCell.getAdjacentCells(pawn, board);
        List<Cell> movableByGod;

        Cell current = board.getGrid()[pawn.getCoordX()][pawn.getCoordY()];

        movableByGod = adjacentCells.stream().filter(c -> (c.getFloor() - current.getFloor()) < 2 && !movableCells.contains(c) && !c.isRoof()).collect(Collectors.toList());

        return movableByGod;
    }

    @Override
    public void executeMove(Cell newCell, Pawn pawn, Model model) {

        Cell oldCell = model.getBoard().getGrid()[pawn.getCoordX()][pawn.getCoordY()];
        Pawn other = newCell.getOccupied();

        BasicAction.MovePawn(oldCell, newCell, pawn);
        if(other != null) {
            BasicAction.MovePawn(newCell, oldCell, other);
            oldCell.setOccupied(other);
            newCell.setOccupied(pawn);
        }

        LightCell lightCellOld = LightConvertion.getLightVersion(oldCell);
        LightCell lightCellNew = LightConvertion.getLightVersion(newCell);

        model.notifyObservers(new DataCell(lightCellNew, lightCellOld));
        model.notifyObservers(new NewAction(false, true, false));
    }

}
