package it.polimi.ingsw.PSP33.model;

import java.awt.*;

/**
 * Pawn class that holds all information related to the state of a pawn piece
 */
public class Pawn {

    /**
     * Value that represent the affiliated player
     */
    private final Color color;

    /**
     * Value of the coordinate x of the position of the palyer
     */
    private int coordX;

    /**
     * Value of the coordinate y of the position of the palyer
     */
    private int coordY;

    /**
     * Constructor of the class
     * @param color color of the related player
     */
    public Pawn(Color color) {
        this.color = color;
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
        return coordX;
    }

    /**
     * Method to set the x value of the position
     * @param coordX Integer value between 0 and 4 (both included)
     */
    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    /**
     * Method to get the value y of the position
     *
     * @return Integer value between 0 and 4 (both included)
     */
    public int getCoordY() {
        return coordY;
    }

    /**
     * Method to set the y value of the position
     * @param coordY Integer value between 0 and 4 (both included)
     */
    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }

    /**
     * Method to set both pawn's coordinates
     * @param coordX x coordinate
     * @param coordY y coordinate
     */
    public void setCoords(int coordX, int coordY) {

        setCoordX(coordX);
        setCoordY(coordY);
    }
}
