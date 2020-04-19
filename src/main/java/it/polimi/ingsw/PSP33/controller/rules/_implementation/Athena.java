package it.polimi.ingsw.PSP33.controller.rules._implementation;

import it.polimi.ingsw.PSP33.controller.rules.enemyTurn.EnemyLimiter;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;

import java.util.List;
import java.util.stream.Collectors;

public class Athena implements EnemyLimiter {

    @Override
    public List<Cell> Limit(List<Cell> cellList, Cell position, Board board) {
        return cellList.stream().filter(c -> c.getFloor() < position.getFloor()).collect(Collectors.toList());
    }
}
