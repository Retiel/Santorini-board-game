package it.polimi.ingsw.PSP33.model.light_version;

import it.polimi.ingsw.PSP33.model.God;
import it.polimi.ingsw.PSP33.utils.enums.Color;

public class LightPlayer {

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
    private final God card;

    public LightPlayer(String name, Color color, God card) {
        this.name = name;
        this.color = color;
        this.card = card;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }

    public God getCard() {
        return card;
    }
}
