package it.polimi.ingsw.PSP33.model;

import it.polimi.ingsw.PSP33.utils.Coord;

import java.awt.*;

/**
 * Pawn class that holds all information related to the state of a pawn piece
 */
public class Pawn {

    /**
     * Value that represent the affiliated player
     */
    private final Color color;

    private Coord coord;

    /**
     * Constructor of the class
     * @param color color of the related player
     */
    public Pawn(Color color) {
        this.color = color;
        this.coord = null;
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

    public Coord getCoord() {
        return coord;
    }
}
