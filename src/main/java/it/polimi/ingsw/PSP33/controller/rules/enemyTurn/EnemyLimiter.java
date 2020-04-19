package it.polimi.ingsw.PSP33.controller.rules.enemyTurn;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;

import java.util.List;

public interface EnemyLimiter {

    List<Cell> Limit(List<Cell> cellList, Cell position, Board board);
}
