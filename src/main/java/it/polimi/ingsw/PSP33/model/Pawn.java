package it.polimi.ingsw.PSP33.model;

import java.awt.*;

/**
 * Pawn class that hold all information related to the state a pawn piece
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
     * @return color of the pawn
     */
    public Color getColor() {
        return color;
    }

    /**
     * Method to get the value x of the position
     *
     * @return Integer value
     */
    public int getCoordX() {
        return coordX;
    }

    /**
     * Method to set the x value of the position
     * @param coordX Integer value
     */
    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    /**
     * Method to get the value y of the position
     *
     * @return Integer value
     */
    public int getCoordY() {
        return coordY;
    }

    /**
     * Method to set the y value of the position
     * @param coordY Integer value
     */
    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }


    public void setCoords(int coordX, int coordY){
        setCoordX(coordX);
        setCoordY(coordY);
    }
}
