package it.polimi.ingsw.PSP33.controller.rules.move;

import it.polimi.ingsw.PSP33.controller.rules.Tools;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;
import java.util.stream.Collectors;

public class MoveBasic implements Move {

    @Override
    public List<Cell> checkMove(Pawn p, Board b) {
        List<Cell> adiacent = Tools.getAdjacentCells(p, b);
        Cell current = b.getGrid()[p.getCoordX()][p.getCoordY()];

        return adiacent.stream().filter(c -> (current.getFloor() - c.getFloor()) < 2 && c.getOccupied() == null && !c.isRoof()).collect(Collectors.toList());
    }

    @Override
    public void executeMove(int x, int y, Pawn p, Board b) {

        Cell newCell = b.getGrid()[x][y];
        Cell oldCell = b.getGrid()[p.getCoordX()][p.getCoordY()];

        p.setCoords(x,y);
        newCell.setOccupied(p);
        oldCell.setOccupied(null);
    }
}
