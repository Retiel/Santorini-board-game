package it.polimi.ingsw.PSP33.model.light_version;

import it.polimi.ingsw.PSP33.utils.enums.Color;

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

    public Color getColor() {
        return color;
    }

    public int getNumber() {
        return number;
    }

    @Override
    public String toString(){
        return color.toString().substring(0,1);
    }
}
