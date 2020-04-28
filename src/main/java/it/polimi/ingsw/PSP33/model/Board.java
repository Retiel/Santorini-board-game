package it.polimi.ingsw.PSP33.model;


/**
 * Board class that hold all information related to the state of the board
 */
public class Board {

    /**
     * Constant of the board's size.
     */
    private final int SIZE = 5;

    /**
     * Matrix of cells used to represent the grid.
     */
    private final Cell[][] grid;

    /**
     * Light version of the board
     */
    private LightBoard lightBoard;

    /**
     * Constructor for the board class.
     */
    public Board() {

        lightBoard = new LightBoard(new Cell.LightCell[SIZE][SIZE]);

        //Initialize grid
        grid = new Cell[SIZE][SIZE];

        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = new Cell(i,j);
                lightBoard.getLightGrid()[i][j] = grid[i][j].getLightCell();
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

    /**
     * Method to get the lightGrid of the board
     *
     * @return return a bidimensional array of LightCell (list of Object)
     */
    public LightBoard getLightBoard() {
        return this.lightBoard;
    }

    /**
     * Method to get the lightGrid of the board
     *
     * @return return a bidimensional array of LightCell (list of Object)
     */
    public class LightBoard{

        /**
         * Light version of the grid
         */
        private final Cell.LightCell[][] lightGrid;

        /**
         * Constructor
         */
        public LightBoard(Cell.LightCell[][] lightGrid) {
            this.lightGrid = lightGrid;
        }

        /**
         * Method to get the lightGrid of the board
         *
         * @return return a bidimensional array of LightCell (list of Object)
         */
        public Cell.LightCell[][] getLightGrid() {
            return lightGrid;
        }
    }
}
