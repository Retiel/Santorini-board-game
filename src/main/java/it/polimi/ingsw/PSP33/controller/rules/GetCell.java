package it.polimi.ingsw.PSP33.controller.rules;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static java.lang.Math.abs;

/**
 * Unique class for all the checks, constraints and methods for the Action a Player or god card can do
 */
public class GetCell {

    private static GetCell instance = new GetCell();
    private GetCell() {}

    /**
     * Method to get the instace of the class
     *
     * @return instance fo the class
     * */
    public static GetCell getInstance() {
        return instance;
    }

    /**
     * Method to get all the adjacent cells to the one where the pawn stands in
     * @param pawn the pawn that create the reference for the initial coordinate
     * @param board the object that represent the board
     *
     * @return List of Cell class object
     */
    public static List<Cell> getAdjacentCells(Pawn pawn, Board board){

        int ix, iy;
        int size = board.getSIZE();
        Cell[][] grid = board.getGrid();
        List<Cell> coordinates = new ArrayList<>();

        for(ix = 0; ix < size; ix++){
            for(iy = 0; iy < size; iy++){

                if(AreAdiacent(pawn.getCoordX(), pawn.getCoordY(), ix, iy)){
                    coordinates.add(grid[ix][iy]);
                }
            }
        }

        return coordinates;
    }

    /**
     * Method to get all the adjacent cells where the player is allowed to move his pawn
     * @param pawn the pawn which the player wants to move
     * @param board the game board onject
     *
     * @return List of Cell class object
     */
    public static List<Cell> getMovableCells(Pawn pawn, Board board){

        List<Cell> adiacent = getAdjacentCells(pawn, board);
        Cell current = board.getGrid()[pawn.getCoordX()][pawn.getCoordY()];

         return adiacent.stream().filter(c -> (c.getFloor() - current.getFloor()) < 2 && c.getOccupied() == null && !c.isRoof()).collect(Collectors.toList());
    }

    /**
     * get all the coordinates of the adjacent cells where the player is allowed to build  his Block or Domes
     * @param pawn the pawn that player wants to use for the building action
     * @param board the game board object
     *
     * @return List of Cell class object
     */
    public static List<Cell> getBuildableCells(Pawn pawn, Board board){

        List<Cell> adiacent = getAdjacentCells(pawn, board);

        return adiacent.stream().filter(c -> c.getOccupied() == null && !c.isRoof()).collect(Collectors.toList());
    }

    /**
     * the method verify if the coordinates are adiacent
     * @param x1 coordinate x of the first
     * @param y1 coordinate y of the first
     * @param x2 coordinate x of the second
     * @param y2 coordinate y of the second
     *
     * @return Boolean
     */
    public static boolean AreAdiacent(int x1, int y1, int x2, int y2){

        int deltaX = abs(x1 - x2);
        int deltaY =  abs(y1 - y2);

        return deltaX <= 1 && deltaY <= 1 && !(deltaX == 0 && deltaY == 0);
    }



    /*other possibilities:
        -   check the change in level after the move to check a possilbe victory
        -   check and constrains obtained due to the god effects (other unique methods? or consider in the same basic method?)
        -   implement with model elements to see if there could be a better solution.
    */

}
