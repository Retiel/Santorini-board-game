package it.polimi.ingsw.PSP33.controller.rules.gods.strategy.win;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

/**
 * BWin condition for all player
 * (also usable as a template to copy/paste)
 */
public class WinBasic implements WinCondition {

    @Override
    public boolean verifyWin(Cell newCell, Board board, Pawn pawn) {

        Cell oldCell = board.getGrid()[pawn.getCoordX()][pawn.getCoordY()];
        return newCell.getFloor() == 3 && oldCell.getFloor() == 2;
    }
}
