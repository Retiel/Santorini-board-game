package it.polimi.ingsw.PSP33.controller.rules.gods;

import it.polimi.ingsw.PSP33.controller.rules.gods.strategy.enemy.Limiter;
import it.polimi.ingsw.PSP33.controller.rules.tools.GetCell;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Limiter by Athena
 *
 */
public class Athena implements Limiter {

    @Override
    public boolean activation(Pawn pawn, Cell newCell, Board board) {
        Cell oldCell = GetCell.getCellAdapter(pawn.getCoord(), board);
        return newCell.getFloor() > oldCell.getFloor();
    }

    @Override
    public List<Cell> limit(List<Cell> cellList, Pawn enemyPawn, Board board) {

        Cell position = GetCell.getCellAdapter(enemyPawn.getCoord(), board);
        return cellList.stream().filter(c -> c.getFloor() <= position.getFloor()).collect(Collectors.toList());
    }
}
