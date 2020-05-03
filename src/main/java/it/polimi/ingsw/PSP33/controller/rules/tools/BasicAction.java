package it.polimi.ingsw.PSP33.controller.rules.tools;

import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

/**
 * TurnAction class, a list of the basic action a player can do in his turn.
 */
public class BasicAction {

    /**
     * The method initialize the pawn position
     * @param pawn pawn to put in the board
     * @param startingCell cell where the selected pawn start
     */
    public static void SetUpPawnPosition(Cell startingCell, Pawn pawn) {
        pawn.setCoords(startingCell.getCoordX(), startingCell.getCoordY());
        startingCell.setOccupied(pawn);
    }

    /**
     * The method a player can use to move a worker on the Board
     * @param oldCell position of the pawn
     * @param newCell new position of the pawn
     * @param pawn the pawn involved
     */
    public static void MovePawn(Cell oldCell, Cell newCell, Pawn pawn) {

        pawn.setCoords(newCell.getCoordX(), newCell.getCoordY());
        oldCell.setOccupied(null);
        newCell.setOccupied(pawn);
    }

    /**
     * the method a player can use to Build a Block (or a Dome) on a Cell
     * @param c the Cell where the player wants to Build
     */
    public static void BuildBlock(Cell c){
        if(c.getFloor() == 3){
            c.setRoof(true);
        }
        else c.setFloor(c.getFloor()+1);
    }

}
