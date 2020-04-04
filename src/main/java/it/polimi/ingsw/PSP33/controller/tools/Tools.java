package it.polimi.ingsw.PSP33.controller.tools;

import com.sun.tools.javac.util.Pair;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.ArrayList;
import java.util.List;

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
     * @param board the board of the game
     *
     * @return List of coordinates adiacent to the pawn
     */
    public static List<Pair<Integer,Integer>> getAdjacentCells(Pawn pawn, Board board){

        int ix, iy;
        List<Pair<Integer,Integer>> coordinates = new ArrayList<>();

        for(ix = 0; ix < Board.getSIZE(); ix++){
            for(iy = 0; iy < Board.getSIZE(); iy++){

                int dX = abs(pawn.getCoordX() - ix);
                int dY = abs(pawn.getCoordY() - iy);

                if(AreAdiacent(dX,dY)){
                    coordinates.add(new Pair<>(ix, iy));
                }
            }
        }

        return coordinates;
    }

    /**
     * get all the coordinates of the adjacent cells where the player is allowed to move his pawn
     * @param pawn the pawn which the player wants to move
     */
    public static void getMovableCells(Pawn pawn){
        //todo: cycle through the adjacent cells and se where the pawn can be moved or not

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
