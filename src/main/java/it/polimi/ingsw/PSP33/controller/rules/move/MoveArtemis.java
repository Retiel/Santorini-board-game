package it.polimi.ingsw.PSP33.controller.rules.move;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

public class MoveArtemis implements Move {
    @Override
    public List<Cell> checkMove(Pawn p, Board b) {
        return null;
    }

    @Override
    public void executeMove(int x, int y, Pawn p, Board b) {

    }
}
