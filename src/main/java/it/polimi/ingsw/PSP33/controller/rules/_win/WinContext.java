package it.polimi.ingsw.PSP33.controller.rules._win;

import it.polimi.ingsw.PSP33.controller.rules.__implementation.Pan;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

/**
 * Class that manage win condition
 */
public class WinContext {

    private WinCondition winCondition;

    public WinContext(String godName) {

        switch (godName){
            case "Pan": this.winCondition = new Pan(); break;
            case "": this.winCondition = new WinBasic();
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
