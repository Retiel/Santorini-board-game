package it.polimi.ingsw.PSP33.model;

import java.util.ArrayList;

/**
 * Board class.
 */
public class Board {

    /**
     * Constant of the board's size.
     */
    private static final int SIZE = 5;

    /**
     * Matrix of cells used to represent the grid.
     */
    private Cell[][] grid;

    /**
     * List of players' pawns.
     */
    private ArrayList<Pawn> pawns;

    /**
     * Constructor for the board class.
     * @param players list of players used to determine how many pawns the game will need.
     */
    public Board(ArrayList<Player> players) {

        //Initialize grid
        grid = new Cell[SIZE][SIZE];
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                grid[i][j] = new Cell();
            }
        }

        //Initialize players' pawns
        pawns = new ArrayList<>();
        for (Player player : players) {
            pawns.add(new Pawn(player.getColor()));
            pawns.add(new Pawn(player.getColor()));
        }
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public ArrayList<Pawn> getPawns() {
        return pawns;
    }
}
