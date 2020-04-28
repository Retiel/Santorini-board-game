package it.polimi.ingsw.PSP33.model;

import it.polimi.ingsw.PSP33.utils.Coord;

import java.awt.*;

/**
 * Pawn class that holds all information related to the state of a pawn piece
 */
public class Pawn {

    /**
     * Light version of the class Pawn
     */
    private final LightPawn lightPawn;

    /**
     * Coordinates of the actual position of the pawn
     */
    private Coord coord;

    /**
     * Constructor of the class
     * @param color color of the related player
     */
    public Pawn(Color color, int number) {
        this.lightPawn = new LightPawn(color, number);
        this.coord = null;
    }

    /**
     * Method to get the color of the pawn
     *
     * @return color of the pawn (java.awt.Color type)
     */
    public Color getColor() {
        return lightPawn.getColor();
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
        return lightPawn.getNumber();
    }

    /**
     * Method to get pawn light version
     *
     * @return Integer
     */
    public LightPawn getLightPawn() {
        return lightPawn;
    }

    public class LightPawn {

        /**
         * Value that represent the affiliated player
         */
        private final Color color;

        /**
         * Value that identify the pawn
         */
        private final int number;

        public LightPawn(Color color, int number) {
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
         * Method to get the numebrID
         *
         * @return Integer
         */
        public int getNumber() {
            return number;
        }
    }
}
