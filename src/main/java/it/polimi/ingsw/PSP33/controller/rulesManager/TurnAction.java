package it.polimi.ingsw.PSP33.controller.rulesManager;

import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.awt.*;

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
     */
    public static void MovePawn(int x, int y, int number) {

        Color color = model.getCurrentPlayer().getColor();
        Pawn pawn = model.getBoard().getPlayerPawn(color, number);
        Cell newCell = model.getBoard().getGrid()[x][y];

        pawn.setCoordX(x);
        pawn.setCoordY(y);
        newCell.setOccupied(pawn);
    }

    /**
     * the method a player can use to Build a Block (or a Dome) on a Cell
     * @param c the Cell where the player wants to Build
     */
    public static void BuildBlock(Cell c){
        if(c.getFloor()==2){
            c.setFloor(c.getFloor()+1);
            c.setRoof(true);
        }
        else c.setFloor(c.getFloor()+1);
    }

}
