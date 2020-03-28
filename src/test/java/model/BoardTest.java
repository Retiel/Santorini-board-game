package model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;

public class BoardTest {

    Board board;

    ArrayList<Player> players;

    @Before
    public void setUp() {
        players = new ArrayList<>();
    }

    @After
    public void tearDown() {
        board = null;
    }

    @Test
    public void initBoard_twoPlayers() {

        players.add(new Player("player1", Color.BLACK));
        players.add(new Player("player2", Color.WHITE));
        board = new Board(players);

        int numberOfCells = 0;
        for (Cell[] row : board.getGrid()) {
            for (Cell cell : row) {
                if (cell != null) {
                    numberOfCells++;
                }
            }
        }
        Assert.assertEquals(numberOfCells, 25);

        ArrayList<Pawn> pawns = board.getPawns();
        Assert.assertEquals(pawns.size(), players.size() * 2);
        Assert.assertEquals(players.get(0).getColor(), pawns.get(0).getColor());
        Assert.assertEquals(players.get(0).getColor(), pawns.get(1).getColor());
        Assert.assertEquals(players.get(1).getColor(), pawns.get(2).getColor());
        Assert.assertEquals(players.get(1).getColor(), pawns.get(3).getColor());
    }

    @Test
    public void initBoard_threePlayers() {

        players.add(new Player("player1", Color.BLACK));
        players.add(new Player("player2", Color.WHITE));
        players.add(new Player("player3", Color.GRAY));
        board = new Board(players);

        int numberOfCells = 0;
        for (Cell[] row : board.getGrid()) {
            for (Cell cell : row) {
                if (cell != null) {
                    numberOfCells++;
                }
            }
        }
        Assert.assertEquals(numberOfCells, 25);

        ArrayList<Pawn> pawns = board.getPawns();
        Assert.assertEquals(pawns.size(), players.size() * 2);
        Assert.assertEquals(players.get(0).getColor(), pawns.get(0).getColor());
        Assert.assertEquals(players.get(0).getColor(), pawns.get(1).getColor());
        Assert.assertEquals(players.get(1).getColor(), pawns.get(2).getColor());
        Assert.assertEquals(players.get(1).getColor(), pawns.get(3).getColor());
        Assert.assertEquals(players.get(2).getColor(), pawns.get(4).getColor());
        Assert.assertEquals(players.get(2).getColor(), pawns.get(5).getColor());
    }
}
