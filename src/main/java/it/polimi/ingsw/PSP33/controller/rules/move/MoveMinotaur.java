package it.polimi.ingsw.PSP33.controller.rules.move;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

import static java.lang.Math.abs;

public class MoveMinotaur implements Move {

    @Override
    public List<Cell> checkMove(Pawn p, Board b) {
        return null;
    }

    @Override
    public void executeMove(int x, int y, Pawn p, Board b) {

        Cell newCell = b.getGrid()[x][y];
        Cell oldCell = b.getGrid()[p.getCoordX()][p.getCoordY()];

        int dX = x - p.getCoordX();
        int dY = y - p.getCoordY();

        Pawn other = newCell.getOccupied();
        Cell otherCell = b.getGrid()[x + dX][y + dY];

        p.setCoords(x,y);
        newCell.setOccupied(p);
        oldCell.setOccupied(null);

        //TODO
    }
}
