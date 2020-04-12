package it.polimi.ingsw.PSP33.controller.rules.move;

import it.polimi.ingsw.PSP33.controller.rules.Tools;
import it.polimi.ingsw.PSP33.controller.rules.TurnAction;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Movement with the rule of Apollo
 *
 */
public class MoveApollo implements Move{

    @Override
    public List<Cell> checkMove(Pawn pawn, Board board) {

        /* Duplicated code ref. -> MoveMinotaur*/
        List<Cell> movableCells = Tools.getMovableCells(pawn, board);

        List<Cell> movableByGod = Tools.getAdjacentCells(pawn, board);
        Cell current = board.getGrid()[pawn.getCoordX()][pawn.getCoordY()];
        movableByGod = movableByGod.stream().filter(c -> (current.getFloor() - c.getFloor()) < 2 && !c.isRoof() && !movableCells.contains(c)).collect(Collectors.toList());

        return movableByGod;
    }

    @Override
    public void executeMove(Cell newCell, Pawn pawn, Board board) {

        Cell oldCell = board.getGrid()[pawn.getCoordX()][pawn.getCoordY()];
        Pawn other = newCell.getOccupied();

        TurnAction.MovePawn(oldCell, newCell, pawn);
        oldCell.setOccupied(other);
    }

}
