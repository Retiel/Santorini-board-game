package it.polimi.ingsw.PSP33.model;

import java.awt.*;

/**
 * Pawn class
 */
public class Pawn {

    private final Color color;
    private int coordX;
    private int coordY;

    public Pawn(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    public int getCoordX() {
        return coordX;
    }

    public void setCoordX(int coordX) {
        this.coordX = coordX;
    }

    public int getCoordY() {
        return coordY;
    }

    public void setCoordY(int coordY) {
        this.coordY = coordY;
    }
}
