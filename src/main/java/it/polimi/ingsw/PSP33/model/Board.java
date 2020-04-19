package it.polimi.ingsw.PSP33.model;

import it.polimi.ingsw.PSP33.events.toClient.MVEvent;
import it.polimi.ingsw.PSP33.utils.patterns.observable.Observable;

/**
 * Board class that hold all information related to the state of the board
 */
public class Board extends Observable<MVEvent> {

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
                grid[i][j] = new Cell(i,j);
            }
        }
    }

    /**
     * Method to get the defined size of the board
     *
     * @return Integer value
     */
    public int getSIZE() {
        return SIZE;
    }

    /**
     * Method to get the grid of the board
     *
     * @return return a bidimensional array of Cell (list of Object)
     */
    public Cell[][] getGrid() {
        return grid;
    }

}
