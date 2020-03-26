package model;

import java.util.ArrayList;

/**
 * Board class
 */
public class Board {

    private static final int size = 5;

    private Cell[][] grid;
    private ArrayList<Pawn> pawns;

    public Board(){
        grid = new Cell[size][size];

        for (Cell[] row : grid){
            for (Cell cell : row){
                cell = new Cell();
            }
        }
    }

    public Cell[][] getGrid() {
        return grid;
    }

    public void setGrid(Cell[][] grid) {
        this.grid = grid;
    }

    public ArrayList<Pawn> getPawns() {
        return pawns;
    }

    public void setPawns(ArrayList<Pawn> pawns) {
        this.pawns = pawns;
    }
}
