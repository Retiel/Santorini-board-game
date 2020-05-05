package it.polimi.ingsw.PSP33.model;

import it.polimi.ingsw.PSP33.utils.enums.Color;

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
    private final Pawn[] pawns;

    /**
     * Constructor of the class
     * @param name name fo the player
     * @param color color of the player
     */
    public Player(String name, Color color) {
        this.name = name;
        this.color = color;

        this.pawns = new Pawn[2];
        this.pawns[0] = new Pawn(color,1);
        this.pawns[1] = new Pawn(color,2);
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

    /**
     * Method to get the player's pawn by number
     * @param pawnNumber number of pawn (1 or 2)
     *
     * @return player's pawn related to pawnNumber
     */
    public Pawn getPawnByNumber(int pawnNumber) {
        return pawns[pawnNumber - 1];
    }

}
