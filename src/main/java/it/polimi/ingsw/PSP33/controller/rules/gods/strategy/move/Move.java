package it.polimi.ingsw.PSP33.controller.rules.gods.strategy.move;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

/**
 * Interface that define the move of the pawns, it uses pattern strategy to differentiate gods effect
 *
 */
public interface Move {

    /**
     * Method to get le list of the cell unlocked by the gods power
     * @param pawn the pawn selected for the action
     * @param board board from model folder
     *
     * @return list of Cell object
     */
    List<Cell> checkMove(Pawn pawn, Board board);

    /**
     * Method to execute the move once selected the cell
     * @param newCell new position where the pawns go
     * @param pawn the pawn in question
     * @param model used for notify
     */
    void executeMove(Cell newCell, Pawn pawn, Model model);

}
