package it.polimi.ingsw.PSP33.controller.rules.move;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

/**
 * Interface that define the move of the pawns and using pattern stategy it differenciate base on the gods effect
 *
 */
public interface Move {

    /**
     * Mehtod to get le list of the possible cell to move the pawn
     * @param board board from model folder
     * @param pawn the pawn selected for the action
     *
     * @return list of Cell object
     */
    List<Cell> checkMove(Pawn pawn, Board board);

    /**
     * Method to execute the move once selected the cell
     * @param newCell new position where the pawns go
     * @param pawn the pawn in question
     * @param board board from model folder
     */
    void executeMove(Cell newCell, Pawn pawn, Board board);

}
