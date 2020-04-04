package it.polimi.ingsw.PSP33.model;

import java.awt.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

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

    public Cell[][] getGrid() {
        return grid;
    }

    public List<Pawn> getPawns() {
        return new ArrayList<>(pawns);
    }

    public Pawn getPlayerPawn(Color color, int pawnNumber){
        return getPawnsByColor(color).get(pawnNumber - 1);
    }

    public List<Pawn> getPawnsByColor(Color color){
        return getPawns().stream().filter(p -> p.getColor() == color).collect(Collectors.toList());
    }

}
