package it.polimi.ingsw.PSP33.controller.tools;

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
public class Tools {

    private static Tools instance = new Tools();
    private Tools() {}

    /**
     * Method to get the instace of the class
     *
     * @return instance
     * */
    public static Tools getInstance() {
        return instance;
    }

    /**
     * get all the coordinates of the adjacent cells to the one where the pawn stands in
     * @param pawn the pawn that create the reference for the initial coordinate
     * @param grid the bidimentional grid that represent the board
     *
     * @return List of coordinates adiacent to the pawn
     */
    public static List<Cell> getAdjacentCells(Pawn pawn, Cell[][] grid, int size){

        int ix, iy;
        List<Cell> coordinates = new ArrayList<>();

        for(ix = 0; ix < size; ix++){
            for(iy = 0; iy < size; iy++){

                int dX = abs(pawn.getCoordX() - ix);
                int dY = abs(pawn.getCoordY() - iy);

                if(AreAdiacent(dX,dY)){
                    coordinates.add(grid[ix][iy]);
                }
            }
        }

        return coordinates;
    }

    /**
     * get all the coordinates of the adjacent cells where the player is allowed to move his pawn
     * @param pawn the pawn which the player wants to move
     * @param board the game board
     */
    public static List<Cell> getMovableCells(Pawn pawn, Board board){

        List<Cell> adiacent = getAdjacentCells(pawn, board.getGrid(), board.getSIZE());
        Cell current = board.getGrid()[pawn.getCoordX()][pawn.getCoordY()];

         return adiacent.stream().filter(c -> abs(c.getFloor() - current.getFloor()) < 2).collect(Collectors.toList());
    }

    /**
     * get all the coordinates of the adjacent cells where the player is allowed to build  his Block or Domes
     * @param pawn the pawn that player wants to use for the building action
     */
    public static void getBuildableCells(Pawn pawn){
        //todo: cycle through the adjacent cells and se where the block or dome can be built or not

    }

    /**
     * the method verify if the coordinates are adiacent
     * @param deltaX absolute difference of x between the coordinate
     * @param deltaY absolute difference of y between the coordinate
     */
    public static boolean AreAdiacent(int deltaX, int deltaY){
        return deltaX <= 1 && deltaY <= 1;
    }

    /*other possibilities:
        -   check the change in level after the move to check a possilbe victory
        -   check and constrains obtained due to the god effects (other unique methods? or consider in the same basic method?)
        -   implement with model elements to see if there could be a better solution.
    */

}
