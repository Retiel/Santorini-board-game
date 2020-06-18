package it.polimi.ingsw.PSP33.controller.rules.gods.strategy.enemy;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

/**
 * Interface that define the god effect over other players pawns, it uses pattern strategy to differentiate gods effect
 *
 */
public interface Limiter {

    /**
     * Method to verify the activation of a limit imposed by a gos
     * @param pawn the pawn selected for the action
     * @param board board from model folder
     * @param cell cell related to the limit effect
     *
     * @return Boolean
     */
    boolean activation(Pawn pawn, Cell cell, Board board);

    /**
     * Method to apply the active limit
     * @param cellList list of possible move of the pawn
     * @param board board from model folder
     * @param enemyPawn pawn who plans to take action
     *
     * @return new List of Cell with the restriction applied
     */
    List<Cell> limit(List<Cell> cellList, Pawn enemyPawn, Board board);
}
