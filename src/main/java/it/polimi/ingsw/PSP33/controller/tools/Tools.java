package it.polimi.ingsw.PSP33.controller.tools;

import it.polimi.ingsw.PSP33.model.Pawn;

/**
 * The class for all the checks and constraints for the Action a Player or god card can do
 * @author Marco Mascheroni
 */
public class Tools {

    private Tools() {}

    //all this methods are temporary, all the inputs and outputs can change based on the need in the controller

    /**
     * get all the coordinates of the adjacent cells to the one where the pawn stands in
     * @param pawn the pawn that create the reference for the initial coordinate
     */
    public void getAdjacentCells(Pawn pawn){
        //todo: cycle through the cells in the board and save the position of the adjacent cells


    }

    /**
     * get all the coordinates of the adjacent cells where the player is allowed to move his pawn
     * @param pawn the pawn which the player wants to move
     */
    public void getMovableCells(Pawn pawn){
        //todo: cycle through the adjacent cells and se where the pawn can be moved or not


    }

    /**
     * get all the coordinates of the adjacent cells where the player is allowed to build  his Block or Domes
     * @param pawn the pawn that player wants to use for the building action
     */
    public void getBuildableCells(Pawn pawn){
        //todo: cycle through the adjacent cells and se where the block or dome can be built or not


    }

    /*other possibilities:
        -   check the change in level after the move to check a possilbe victory
        -   check and constrains obtained due to the god effects (other unique methods? or consider in the same basic method?)
        -   implement with model elements to see if there could be a better solution.
    */

}
