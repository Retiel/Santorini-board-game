package it.polimi.ingsw.PSP33.controller.godsRules;

import it.polimi.ingsw.PSP33.controller.Tools;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;
import java.util.stream.Collectors;

public class MoveApollo implements Move{

    @Override
    public List<Cell> checkMove(Pawn p, Board b) {
        List<Cell> adiacent = Tools.getAdjacentCells(p, b.getGrid(), b.getSIZE());
        Cell current = b.getGrid()[p.getCoordX()][p.getCoordY()];

        return adiacent.stream().filter(c -> (current.getFloor() - c.getFloor()) < 2 && !c.isRoof()).collect(Collectors.toList());
    }

    @Override
    public void executeMove(int x, int y, Pawn p, Board b) {

        Cell newCell = b.getGrid()[x][y];
        Cell oldCell = b.getGrid()[p.getCoordX()][p.getCoordY()];
        Pawn other = newCell.getOccupied();

        p.setCoords(x,y);
        newCell.setOccupied(p);
        oldCell.setOccupied(other);
    }

}
