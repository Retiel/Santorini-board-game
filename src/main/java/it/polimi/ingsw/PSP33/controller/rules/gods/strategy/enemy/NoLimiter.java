package it.polimi.ingsw.PSP33.controller.rules.gods.strategy.enemy;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

public class NoLimiter implements Limiter{

    @Override
    public boolean activation(Pawn pawn, Cell cell, Board board) {
        return true;
    }

    @Override
    public List<Cell> limit(List<Cell> cellList, Pawn enemyPawn, Board board) {
        return cellList;
    }
}
