package it.polimi.ingsw.PSP33.model;

import java.awt.*;

/**
 * Player class that holds the data related to the player
 */
public class Player {

    /**
     * Name of the player
     */
    private final String name;

    /**
     * Color fo the player (unique to each player)
     */
    private final Color color;

    /**
     * God card the player choose
     */
    private God card;

    /**
     * Player's pawns
     */
    private Pawn[] pawns;

    /**
     * Constructor of the class
     * @param name name fo the player
     * @param color color of the player
     * @param card god card choose by the player
     */
    public Player(String name, Color color, God card) {
        this.name = name;
        this.color = color;
        this.card = card;

        this.pawns = new Pawn[2];
        this.pawns[0] = new Pawn(color);
        this.pawns[1] = new Pawn(color);
    }

    /**
     * Method to get the name of the player
     *
     * @return String
     */
    public String getName() {
        return name;
    }

    /**
     * Method to get the color of the player
     *
     * @return java.awt.Color type
     */
    public Color getColor() {
        return color;
    }

    /**
     * Method to get the card refence
     *
     * @return God class object
     */
    public God getCard() {
        return card;
    }

    /**
     * Method to set the card
     * @param card God class object
     */
    public void setCard(God card) {
        this.card = card;
    }

    /**
     * Method to get the player's pawns
     *
     * @return Array of 2 pawns
     */
    public Pawn[] getPawns() {
        return pawns;
    }
}
