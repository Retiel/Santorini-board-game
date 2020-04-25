package it.polimi.ingsw.PSP33.controller.rules.__implementation;

import it.polimi.ingsw.PSP33.controller.rules.GetCell;
import it.polimi.ingsw.PSP33.controller.rules._enemyTurn.Limiter;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;
import java.util.stream.Collectors;

public class Athena implements Limiter {

    @Override
    public List<Cell> Limit(List<Cell> cellList, Pawn pawn, Board board) {

        Cell position = GetCell.getCellAdapter(pawn.getCoord(), board);
        return cellList.stream().filter(c -> c.getFloor() < position.getFloor()).collect(Collectors.toList());
    }
}
