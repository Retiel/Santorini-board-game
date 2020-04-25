package it.polimi.ingsw.PSP33.controller.rules._build;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

/**
 * Interface that define the build of the pawns and using pattern stategy it differenciate base on the gods effect
 *
 */
public interface Build {

    /**
     * Mehtod to get le list of the possible cell to build
     * @param pawn the pawn selected for the action
     * @param board board from model folder
     *
     * @return list of Cell object
     */
    List<Cell> checkBuild(Pawn pawn, Board board);

    /**
     * Method to execute the builde once selected the cell
     * @param cellToBuild new position where to build
     * @param trigger multipurpose trigger for differente effects
     * @param model used for the notify
     */
    void executeBuild(Cell cellToBuild, boolean trigger, Model model);
}
