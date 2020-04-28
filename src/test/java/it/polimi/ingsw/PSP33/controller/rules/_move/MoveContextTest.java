package it.polimi.ingsw.PSP33.controller.rules._move;

import it.polimi.ingsw.PSP33.controller.rules.tools.GetCell;
import it.polimi.ingsw.PSP33.controller.rules._build.BuildContext;
import it.polimi.ingsw.PSP33.model.*;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class MoveContextTest {

    private static Model testModel;
    private static Board testBoard;
    private static Player testPlayer1;
    private static Player testPlayer2;

    private static MoveContext testSample;

/*
      NOTE! use this legend as a reference for the diffent test cases

      legend:
      * -> roof = true
      0,...,3 -> floor number
      p -> pawn position player1
      e -> pawn position player2

      -> Graphical rappresentation of the board state: (using matrix standard refence with the upper left stating as a [0,0] coordinates)
                 _______ _______ _______ _______ _______
                |       |       |       |       |       |
                |   0   |   1   |   0   |   0   |   0   |
                |_______|_______|_______|_______|_______|
                | *     | p     |   *   |       |       |
                |   0   |   1   |   3   |   0   |   0   |
                |_______|_______|_______|_______|_______|
                | e     | p     |       |       |       |
                |   0   |   3   |   0   |   0   |   0   |
                |_______|_______|_______|_______|_______|
                |   *   |       | e     |       |       |
                |   0   |   2   |   2   |   0   |   0   |
                |_______|_______|_______|_______|_______|
                |       |       |       |       |       |
                |   0   |   1   |   0   |   0   |   0   |
                |_______|_______|_______|_______|_______|

*/

    @Before
    public void setUp(){

        List<Player> testPlayers = new ArrayList<>();

        testPlayer1 = new Player("testPlayer1", Color.BLACK);
        testPlayer2 = new Player("testPlayer2", Color.GREEN);

        testPlayers.add(testPlayer1);
        testPlayers.add(testPlayer2);

        testModel = new Model(testPlayers);
        testBoard = testModel.getBoard();

        testBoard.getGrid()[0][1].setFloor(1);
        testBoard.getGrid()[1][1].setFloor(1);
        testBoard.getGrid()[1][2].setFloor(3);
        testBoard.getGrid()[2][1].setFloor(3);
        testBoard.getGrid()[3][1].setFloor(2);
        testBoard.getGrid()[3][2].setFloor(2);
        testBoard.getGrid()[4][1].setFloor(1);

        testBoard.getGrid()[1][0].setRoof(true);
        testBoard.getGrid()[1][2].setRoof(true);
        testBoard.getGrid()[3][0].setRoof(true);


        testPlayer1.getPawns()[0].setCoords(1,1);
        testBoard.getGrid()[1][1].setOccupied(testPlayer1.getPawns()[0]);

        testPlayer1.getPawns()[1].setCoords(2,1);
        testBoard.getGrid()[2][1].setOccupied(testPlayer1.getPawns()[1]);

        testPlayer2.getPawns()[0].setCoords(2,0);
        testBoard.getGrid()[2][0].setOccupied(testPlayer2.getPawns()[0]);

        testPlayer2.getPawns()[1].setCoords(3,2);
        testBoard.getGrid()[3][2].setOccupied(testPlayer2.getPawns()[1]);

        testSample = new MoveContext("");
        assertTrue(testSample.checkMove(testPlayer1.getPawns()[0], testBoard).isEmpty());

        Pawn testPawnPlayer1 = testPlayer1.getPawnByNumber(2);

        List<Cell> movableCells = GetCell.getMovableCells(testPawnPlayer1, testBoard);
        List<Cell> sample1 = new ArrayList<>();
        sample1.add(testBoard.getGrid()[2][2]);
        sample1.add(testBoard.getGrid()[3][1]);

        assertEquals(2, movableCells.size());
        for (Cell cell : movableCells){
            assertTrue(sample1.contains(cell));
        }
    }

    @Test
    public void verifyPrometheus() {

        testSample = new MoveContext("Prometheus");
        BuildContext testSampleBuild = new BuildContext("Prometheus");

        /* pawn position ()*/
        Pawn testPawn = testPlayer1.getPawnByNumber(2);

        testExecMethod(testSample,3,1, testPawn);

        testSampleBuild.execBuild(4,1,  true, testModel);
        testSampleBuild.execBuild(4,1, true, testModel);
        assertEquals(3, testBoard.getGrid()[4][1].getFloor());

        List<Cell> sample2 = new ArrayList<>();
        sample2.add(testBoard.getGrid()[2][2]);
        sample2.add(testBoard.getGrid()[4][0]);
        sample2.add(testBoard.getGrid()[4][2]);

        testCheckMethod(testSample, testPawn, sample2);
        testExecMethod(testSample,4,0, testPawn);
    }

    @Test
    public void verifyMinotaur() {
        testSample = new MoveContext("Minotaur");

        /* pawn position (2,0) */
        Pawn testPawn = testPlayer2.getPawnByNumber(1);

        List<Cell> sample = new ArrayList<>();
        sample.add(testBoard.getGrid()[1][1]);

        testCheckMethod(testSample, testPawn, sample);
        testExecMethod(testSample, 2,1 ,testPawn);

        Pawn pawnInvolved = testPlayer1.getPawnByNumber(2);
        Cell cell = testBoard.getGrid()[2][2];

        assertThat(cell.getOccupied(), is(pawnInvolved));
        assertEquals(2, pawnInvolved.getCoordX());
        assertEquals(2, pawnInvolved.getCoordY());
    }

    @Test
    public void verifyApollo() {

        MoveContext testSample = new MoveContext("Apollo");

        /* pawn position (2,1) */
        Pawn testPawn = testPlayer1.getPawnByNumber(2);

        List<Cell> sample = new ArrayList<>();
        sample.add(testBoard.getGrid()[1][1]);
        sample.add(testBoard.getGrid()[2][0]);
        sample.add(testBoard.getGrid()[3][2]);

        testCheckMethod(testSample, testPawn, sample);
        testExecMethod(testSample, 3,2 , testPawn);

        Pawn testP2 = testPlayer2.getPawnByNumber(2);

        assertEquals(2, testP2.getCoordX());
        assertEquals(1, testP2.getCoordY());
    }

    private void testCheckMethod(MoveContext moveContext, Pawn pawn, List<Cell> sample){

        List<Cell> test = moveContext.checkMove(pawn, testBoard);

        assertEquals(sample.size(), test.size());
        for (Cell cell : test){
            assertTrue(sample.contains(cell));
        }

    }

    private void testExecMethod(MoveContext moveContext, int x, int y, Pawn pawn){

        moveContext.execMove(x, y, pawn, testModel);
        Cell newPos = testBoard.getGrid()[x][y];

        assertThat(newPos.getOccupied(), is(pawn));
        assertEquals(pawn.getColor(), newPos.getOccupied().getColor());
        assertEquals(x, pawn.getCoordX());
        assertEquals(y, pawn.getCoordY());
    }


}