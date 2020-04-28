package it.polimi.ingsw.PSP33.controller.rules.gods.strategy.win;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

public interface WinCondition {

    boolean verifyWin(Cell newCell, Board board, Pawn pawn);
}
