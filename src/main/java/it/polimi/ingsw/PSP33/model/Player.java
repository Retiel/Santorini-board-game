package it.polimi.ingsw.PSP33.model;

import java.awt.*;

/**
 * Player class
 */
public class Player {

    private final String name;
    private final Color color;
    private int card;

    private Pawn pawn1;
    private Pawn pawn2;

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

    public Pawn getPawn1() {
        return pawn1;
    }

    public void setPawn1(Pawn pawn1) {
        this.pawn1 = pawn1;
    }

    public Pawn getPawn2() {
        return pawn2;
    }

    public void setPawn2(Pawn pawn2) {
        this.pawn2 = pawn2;
    }

}
