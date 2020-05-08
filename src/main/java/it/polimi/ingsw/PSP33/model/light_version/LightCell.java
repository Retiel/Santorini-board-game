package it.polimi.ingsw.PSP33.model.light_version;

import it.polimi.ingsw.PSP33.utils.Coord;

public class LightCell {
    /**
     * Coordinates of the cell
     */
    private final Coord coord;

    /**
     * Integer that represent the pieces of contruction, except the roof
     */
    private final int floor;

    /**
     * Boolean that represent the roof piece in the game
     */
    private final boolean roof;

    /**
     * Reference to the pawn object that occupies the cell
     */
    private final LightPawn occupied;

    public LightCell(Coord coord, int floor, boolean roof, LightPawn occupied) {
        this.coord = coord;
        this.floor = floor;
        this.roof = roof;
        this.occupied = occupied;
    }

    public Coord getCoord() {
        return coord;
    }

    public int getFloor() {
        return floor;
    }

    public boolean isRoof() {
        return roof;
    }

    public LightPawn getOccupied() {
        return occupied;
    }
}
