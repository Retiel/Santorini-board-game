package it.polimi.ingsw.PSP33.controller.rules;

import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;

/**
 * TurnAction class, a list of the basic action a player can do in his turn.
 */
public class TurnAction {

    private static Model model;

    /**
     * TurnAction Constructor
     */
    public TurnAction(Model inModel) {
        model = inModel;
    }


    /**
     * The method initialize the game
     */
    public static void SetUpGame() {

    }

    /**
     * The method a player can use to move a worker on the Board
     * @param x the new x coordinate
     * @param y the new y coordinate
     * @param p the pawn
     */
    public static void MovePawn(int x, int y, Pawn p) {


        Cell newCell = model.getBoard().getGrid()[x][y];
        Cell oldCell = model.getBoard().getGrid()[p.getCoordX()][p.getCoordY()];

        p.setCoords(x,y);
        oldCell.setOccupied(null);
        newCell.setOccupied(p);
    }

    /**
     * the method a player can use to Build a Block (or a Dome) on a Cell
     * @param c the Cell where the player wants to Build
     */
    public static void BuildBlock(Cell c){
        if(c.getFloor()==3){
            c.setRoof(true);
        }
        else c.setFloor(c.getFloor()+1);
    }

}
