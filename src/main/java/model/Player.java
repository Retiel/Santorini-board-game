package model;

import java.awt.*;

/**
 * Player class
 */
public class Player {

    private final String name;
    private final Color color;
    private int card;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public int getCard() {
        return card;
    }

    public void setCard(int card) {
        this.card = card;
    }
}
