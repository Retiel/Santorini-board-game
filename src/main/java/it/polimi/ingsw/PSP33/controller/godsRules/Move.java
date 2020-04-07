package it.polimi.ingsw.PSP33.controller.godsRules;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

public interface Move {

    List<Cell> checkMove(Pawn p, Board b);

    void executeMove(int x, int y, Pawn p, Board b);

}
