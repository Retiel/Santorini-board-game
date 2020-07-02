package it.polimi.ingsw.PSP33.model;

import it.polimi.ingsw.PSP33.utils.Coord;
import it.polimi.ingsw.PSP33.utils.enums.Color;

/**
 * Pawn class that holds all information related to the state of a pawn piece
 */
public class Pawn {

    /**
     * Value that represent the affiliated player
     */
    private final Color color;

    /**
     * Coordinates of the actual position of the pawn
     */
    private Coord coord;

    /**
     * Value that identify the pawn
     */
    private final int number;

    /**
     * History of the pawn turn
     */
    private Coord oldMove;
    private Coord oldBuild;
    private Coord oldExtra;

    /**
     * Constructor of the class
     * @param color color of the related player
     * @param number pawn number
     */
    public Pawn(Color color, int number) {
        this.color = color;
        this.number = number;
    }

    /**
     * Method to get the color of the pawn
     *
     * @return color of the pawn (java.awt.Color type)
     */
    public Color getColor() {
        return color;
    }

    /**
     * Method to get the value x of the position
     *
     * @return Integer value between 0 and 4 (both included)
     */
    public int getCoordX() {
        return coord.getX();
    }


    /**
     * Method to get the value y of the position
     *
     * @return Integer value between 0 and 4 (both included)
     */
    public int getCoordY() {
        return coord.getY();
    }


    /**
     * Method to set both pawn's coordinates
     * @param coordX x coordinate
     * @param coordY y coordinate
     */
    public void setCoords(int coordX, int coordY) {

        this.coord = new Coord(coordX, coordY);
    }

    /**
     * Method to retrieve coordinates position
     *
     * @return Coord object
     */
    public Coord getCoord() {
        return coord;
    }

    /**
     * Method to get the numebrID
     *
     * @return Integer
     */
    public int getNumber() {
        return number;
    }


    /**
     *Method to get history of the pawn move actions
     *
     * @return Coord class Object
     */
    public Coord getOldMove() {
        return oldMove;
    }

    /**
     * Method to set history of the pawn move actions
     * @param oldMove position before the execute move action
     */
    public void setOldMove(Coord oldMove) {
        this.oldMove = oldMove;
    }

    /**
     *Method to get history of the pawn build actions
     *
     * @return Coord class Object
     */
    public Coord getOldBuild() {
        return oldBuild;
    }

    /**
     * Method to set history of the pawn nuild actions
     * @param oldBuild target cell of the build action
     */
    public void setOldBuild(Coord oldBuild) {
        this.oldBuild = oldBuild;
    }

    /**
     *Method to get history of the pawn extra actions
     *
     * @return Coord class Object
     */
    public Coord getOldExtra() {
        return oldExtra;
    }

    /**
     * Method to set history of the pawn extra actions
     * @param oldExtra position or target cell of the extra action
     */
    public void setOldExtra(Coord oldExtra) {
        this.oldExtra = oldExtra;
    }

    @Override
    public String toString(){
        return color.toString().substring(0,1);
    }
}
