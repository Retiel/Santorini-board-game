package it.polimi.ingsw.PSP33.controller.rules.move;

import it.polimi.ingsw.PSP33.controller.rules.Tools;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;
import java.util.stream.Collectors;

public class MoveApollo implements Move {

    @Override
    public List<Cell> checkMove(Pawn pawn, Board board) {
        List<Cell> adiacent = Tools.getAdjacentCells(pawn, board);
        Cell current = board.getCellByCoordinates(pawn.getCoordX(), pawn.getCoordY());

        return adiacent.stream().filter(c -> (
                current.getFloor() - c.getFloor()) < 2
                && !c.isRoof())
                .collect(Collectors.toList());
    }

    @Override
    public void executeMove(Pawn pawn, Board board, int x, int y) {
        Cell newCell = board.getCellByCoordinates(x, y);
        Cell oldCell = board.getCellByCoordinates(pawn.getCoordX(), pawn.getCoordY());
        Pawn other = newCell.getOccupied();

        pawn.setCoords(x,y);

        newCell.setOccupied(pawn);
        oldCell.setOccupied(other);
    }
}
