package controller.rules;

import model.Board;
import model.Cell;
import model.Pawn;

import java.awt.*;

/**
 * TurnAction class, a list of the basic action a player can do in his turn.
 * @author Marco Mascheroni
 */
public class TurnAction {

    /**
     * TurnAction Constructor
     */
    private TurnAction() {}

    /**
     * The method a player can use to move a worker on the Board
     * @param x the new x coordinate
     * @param y the new y coordinate
     * @param p the pawn the player wants to move
     */
    public void MovePawn(int x, int y, Pawn p){
        p.setCoords(x,y);
    }

    /**
     * the method a player can use to Build a Block (or a Dome) on a Cell
     * @param c the Cell where the player wants to Build
     */
    public void BuildBlock(Cell c){
        if(c.getFloor()==2){
            c.setFloor(c.getFloor()+1);
            c.setRoof(true);
        }
        else c.setFloor(c.getFloor()+1);
    }


}
