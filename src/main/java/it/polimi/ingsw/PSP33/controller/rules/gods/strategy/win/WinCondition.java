package it.polimi.ingsw.PSP33.controller.rules.gods.strategy.win;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

/**
 * Interface that define the win condition, it uses pattern strategy to differentiate gods effect
 *
 */
public interface WinCondition {

    /**
     * Method to verify if the player won the game
     * @param pawn the pawn selected for the action
     * @param board board from model folder
     * @param newCell cell where pawn plans to move
     *
     * @return Boolean
     */
    boolean verifyWin(Cell newCell, Board board, Pawn pawn);
}
