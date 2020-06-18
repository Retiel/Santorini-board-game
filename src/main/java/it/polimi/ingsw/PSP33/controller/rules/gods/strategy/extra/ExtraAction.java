package it.polimi.ingsw.PSP33.controller.rules.gods.strategy.extra;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

/**
 * Interface that define the extra action granted by the god card, it uses pattern strategy to differentiate gods effect
 *
 */
public interface ExtraAction {

    /**
     * Method to get possible cell where to apply extra effect
     * @param pawn the pawn selected for the action
     * @param board board from model folder
     *
     * @return Boolean
     */
    List<Cell> checkPlusAction(Pawn pawn, Board board);

    /**
     * Method to apply the extra action
     * @param pawn the pawn selected for the action
     * @param cell cell selected for the extra action
     * @param model Model from folder model
     *
     */
    void applyAction(Cell cell, Pawn pawn, Model model);
}
