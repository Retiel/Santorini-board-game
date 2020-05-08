package it.polimi.ingsw.PSP33.model.light_version;

public class LightBoard {

    /**
     * Matrix of cells used to represent the grid.
     */
    private final LightCell[][] grid;

    public LightBoard(LightCell[][] grid) {
        this.grid = grid;
    }

    public LightCell[][] getGrid() {
        return grid;
    }
}
