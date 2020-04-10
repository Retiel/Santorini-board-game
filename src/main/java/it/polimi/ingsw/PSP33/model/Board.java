package it.polimi.ingsw.PSP33.model;


/**
 * Board class that holds all information related to the state of the board
 */
public class Board {

    /**
     * Constant of the board's size.
     */
    private final int SIZE = 5;

    /**
     * Matrix of cells used to represent the grid.
     */
    private Cell[][] grid;

    /**
     * Constructor for the board class.
     */
    public Board() {

        //Initialize grid
        grid = new Cell[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = new Cell();
            }
        }
    }

    /**
     * Method to get the defined size of the board
     *
     * @return Integer value 5
     */
    public int getSIZE() {
        return SIZE;
    }

    /**
     * Method to get the grid of the board
     *
     * @return bidimensional array of Cell class object
     */
    public Cell[][] getGrid() {
        return grid;
    }

    public Cell getCellByCoordinates(int coordX, int coordY) {
        return this.grid[coordX][coordY];
    }
}
