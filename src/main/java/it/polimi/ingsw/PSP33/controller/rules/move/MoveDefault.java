package it.polimi.ingsw.PSP33.controller.rules.move;

import it.polimi.ingsw.PSP33.controller.rules.Tools;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;
import java.util.stream.Collectors;

public class MoveDefault implements Move {

    @Override
    public List<Cell> checkMove(Pawn pawn, Board board) {
        List<Cell> adjacentCells = Tools.getAdjacentCells(pawn, board);
        Cell current = board.getCellByCoordinates(pawn.getCoordX(), pawn.getCoordY());

        return adjacentCells.stream().filter(c -> (
                current.getFloor() - c.getFloor()) < 2
                && c.getOccupied() == null
                && !c.isRoof())
                .collect(Collectors.toList());
    }

    @Override
    public void executeMove(Pawn pawn, Board board, int x, int y) {
        Cell newCell = board.getCellByCoordinates(x, y);
        Cell oldCell = board.getCellByCoordinates(pawn.getCoordX(), pawn.getCoordY());

        pawn.setCoords(x,y);

        newCell.setOccupied(pawn);
        oldCell.setOccupied(null);
    }
}
