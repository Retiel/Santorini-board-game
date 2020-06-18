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
    public static void setUpPawnPosition(Cell startingCell, Pawn pawn) {
        pawn.setCoords(startingCell.getCoordX(), startingCell.getCoordY());
        startingCell.setOccupied(pawn);
    }

    /**
     * The method a player can use to move a worker on the Board
     * @param oldCell position of the pawn
     * @param newCell new position of the pawn
     * @param pawn the pawn involved
     */
    public static void movePawn(Cell oldCell, Cell newCell, Pawn pawn) {

        pawn.setCoords(newCell.getCoordX(), newCell.getCoordY());
        oldCell.setOccupied(null);
        newCell.setOccupied(pawn);
    }

    /**
     * the method a player can use to Build a Block (or a Dome) on a Cell
     * @param cell the Cell where the player wants to Build
     */
    public static void buildBlock(Cell cell){
        if(cell.getFloor() == 3){
            cell.setRoof(true);
        }
        else cell.setFloor(cell.getFloor()+1);
    }

}
