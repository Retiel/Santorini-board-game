package it.polimi.ingsw.PSP33.controller.rules._enemyTurn;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

public interface Limiter {

    List<Cell> Limit(List<Cell> cellList, Pawn pawn, Board board);
}
