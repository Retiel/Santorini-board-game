package it.polimi.ingsw.PSP33.controller.rules._implementation;

import it.polimi.ingsw.PSP33.controller.rules.GetCell;
import it.polimi.ingsw.PSP33.controller.rules.BasicAction;
import it.polimi.ingsw.PSP33.controller.rules.move.Move;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

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

        List<Cell> movableByGod = GetCell.getAdjacentCells(pawn, board);
        Cell current = board.getGrid()[pawn.getCoordX()][pawn.getCoordY()];
        movableByGod = movableByGod.stream().filter(c -> (current.getFloor() - c.getFloor()) < 2 && !c.isRoof() && !movableCells.contains(c)).collect(Collectors.toList());

        return movableByGod;
    }

    @Override
    public void executeMove(Cell newCell, Pawn pawn, Board board) {

        Cell oldCell = board.getGrid()[pawn.getCoordX()][pawn.getCoordY()];
        Pawn other = newCell.getOccupied();

        BasicAction.MovePawn(oldCell, newCell, pawn);
        oldCell.setOccupied(other);
    }

}
