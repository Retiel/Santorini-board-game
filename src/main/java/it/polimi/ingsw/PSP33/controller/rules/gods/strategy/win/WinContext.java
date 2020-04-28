package it.polimi.ingsw.PSP33.controller.rules.gods.strategy.win;

import it.polimi.ingsw.PSP33.controller.rules.gods.Pan;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;
import it.polimi.ingsw.PSP33.utils.enums.Gods;

/**
 * Class that manages win condition
 */
public class WinContext {

    private WinCondition winCondition;

    public WinContext(Gods godName) {

        switch (godName){
            case PAN: this.winCondition = new Pan(); break;
            default:
                this.winCondition = new WinBasic();
        }

    }

    /**
     * Method to check win condition
     * @param board board of the game
     * @param pawn pawn involved
     * @param newCell next placement of the pawn
     *
     * @return Boolean
     */
    public boolean checkWinCondition(Board board, Pawn pawn, Cell newCell){
        return winCondition.verifyWin(newCell, board, pawn);
    }


}
