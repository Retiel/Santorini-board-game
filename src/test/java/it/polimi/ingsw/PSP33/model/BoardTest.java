package it.polimi.ingsw.PSP33.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoardTest {

    private Board board;

    private ArrayList<Player> players;

    @Before
    public void setUp() {
        players = new ArrayList<>();
    }

    @After
    public void tearDown() {
        board = null;
    }

    @Test
    public void setPawns_correctInput_correctOutput(){

        Player testPlayer1 = new Player("player1", Color.BLACK);
        Player testPlayer2 = new Player("player2", Color.WHITE);

        players.add(testPlayer1);
        players.add(testPlayer2);

        board = new Board(players);

        List<Pawn> testSample = board.getPawns();

        Assert.assertEquals(4, testSample.size());

        Assert.assertEquals(testPlayer1.getPawn1(), testSample.get(0));
        Assert.assertEquals(testPlayer1.getPawn2(), testSample.get(1));
        Assert.assertEquals(testPlayer2.getPawn1(), testSample.get(2));
        Assert.assertEquals(testPlayer2.getPawn2(), testSample.get(3));

        testSample.remove(testPlayer2.getPawn1());
        board.setPawns(testSample);

        Assert.assertEquals(3, board.getPawns().size());

        Assert.assertEquals(testPlayer1.getPawn1(), board.getPawns().get(0));
        Assert.assertEquals(testPlayer1.getPawn2(), board.getPawns().get(1));
        Assert.assertEquals(testPlayer2.getPawn2(), board.getPawns().get(2));
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

        List<Pawn> pawns = board.getPawns();
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

        List<Pawn> pawns = board.getPawns();
        Assert.assertEquals(pawns.size(), players.size() * 2);
        Assert.assertEquals(players.get(0).getColor(), pawns.get(0).getColor());
        Assert.assertEquals(players.get(0).getColor(), pawns.get(1).getColor());
        Assert.assertEquals(players.get(1).getColor(), pawns.get(2).getColor());
        Assert.assertEquals(players.get(1).getColor(), pawns.get(3).getColor());
        Assert.assertEquals(players.get(2).getColor(), pawns.get(4).getColor());
        Assert.assertEquals(players.get(2).getColor(), pawns.get(5).getColor());
    }
}
