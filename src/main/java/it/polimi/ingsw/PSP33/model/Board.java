package it.polimi.ingsw.PSP33.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
    private Cell[][] grid;

    /**
     * List of players' pawns.
     */
    private List<Pawn> pawns;

    /**
     * Constructor for the board class.
     * @param players list of players used to determine how many pawns the game will need.
     */
    public Board(List<Player> players) {

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
     * method to get the list of all pawn
     *
     * @return List of Pawns (list of Object)
     */
    public List<Pawn> getPawns() {
        return new ArrayList<>(pawns);
    }

    /**
     * Method to get one of the pawn of the player
     * @param color the color that identifiy the player
     * @param pawnNumber number of the selected pawn
     *
     * @return object Pawn
     */
    public Pawn getPlayerPawn(Color color, int pawnNumber){
        return getPawnsByColor(color).get(pawnNumber - 1);
    }

    /**
     * Method to get all pawns of the player
     * @param color the color that identifiy the player
     *
     * @return List of Pawns (list of Object)
     */
    public List<Pawn> getPawnsByColor(Color color){
        return getPawns().stream().filter(p -> p.getColor() == color).collect(Collectors.toList());
    }

}
