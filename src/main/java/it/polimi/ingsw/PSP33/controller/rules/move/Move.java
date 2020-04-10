package it.polimi.ingsw.PSP33.controller.rules.move;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

public interface Move {

    List<Cell> checkMove(Pawn pawn, Board board);

    void executeMove(Pawn pawn, Board board, int x, int y);
}
