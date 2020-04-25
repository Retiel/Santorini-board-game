package it.polimi.ingsw.PSP33.controller.rules.__implementation;

import it.polimi.ingsw.PSP33.controller.rules.GetCell;
import it.polimi.ingsw.PSP33.controller.rules.BasicAction;
import it.polimi.ingsw.PSP33.controller.rules._move.Move;
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
        List<Cell> adjacentCells = GetCell.getAdjacentCells(pawn, board);
        List<Cell> movableByGod;

        Cell current = board.getGrid()[pawn.getCoordX()][pawn.getCoordY()];

        movableByGod = adjacentCells.stream().filter(c -> (c.getFloor() - current.getFloor()) < 2 && !movableCells.contains(c) && !c.isRoof()).collect(Collectors.toList());

        return movableByGod;
    }

    @Override
    public void executeMove(Cell newCell, Pawn pawn, Board board) {

        Cell oldCell = board.getGrid()[pawn.getCoordX()][pawn.getCoordY()];
        Pawn other = newCell.getOccupied();

        BasicAction.MovePawn(oldCell, newCell, pawn);
        BasicAction.MovePawn(newCell, oldCell, other);

        oldCell.setOccupied(other);
        newCell.setOccupied(pawn);
    }

}
