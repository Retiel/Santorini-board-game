package it.polimi.ingsw.PSP33.controller.rules.gods;

import it.polimi.ingsw.PSP33.controller.rules.gods.strategy.win.WinCondition;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

/**
 * Win condition by Pan
 *
 */
public class Pan implements WinCondition {

    @Override
    public boolean verifyWin(Cell newCell, Board board, Pawn pawn) {

        Cell oldCell = board.getGrid()[pawn.getCoordX()][pawn.getCoordY()];
        return (newCell.getFloor() == 3 && oldCell.getFloor() == 2) || (oldCell.getFloor() - newCell.getFloor()) >= 2;
    }
}
