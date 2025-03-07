package it.polimi.ingsw.PSP33.controller.rules.gods.strategy.extra;

import it.polimi.ingsw.PSP33.controller.rules.tools.DataBuffer;
import it.polimi.ingsw.PSP33.controller.rules.tools.GetCell;
import it.polimi.ingsw.PSP33.model.*;
import it.polimi.ingsw.PSP33.utils.Coord;
import it.polimi.ingsw.PSP33.utils.enums.Color;
import it.polimi.ingsw.PSP33.utils.enums.Gods;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ExtraContextTest {


    private static Model testModel;
    private static Board testBoard;
    private static Player testPlayer1;
    private static Player testPlayer2;
    private static DataBuffer sampleDataBuffer;

    private static ExtraContext testSample;

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

        testPlayer1 = new Player("testPlayer1", Color.BLUE);
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

        sampleDataBuffer = new DataBuffer();
        testSample = new ExtraContext(Gods.NOGOD);
        assertTrue(testSample.extraRequest(testPlayer1.getPawns()[0], testBoard).isEmpty());
    }

    @Test
    public void verifyBasic() {

        testSample = new ExtraContext(Gods.NOGOD);
        Pawn samplePawn = testPlayer1.getPawnByNumber(1);
        Cell sampleOldCell = testBoard.getGrid()[1][3];
        samplePawn.setOldMove(sampleOldCell.getCoord());

        List<Cell> sample = new ArrayList<>();

        testCheckMethod(testSample, samplePawn, sample);

        testSample.execAction(new Coord(1,3), samplePawn, testModel,true);
        assertEquals(1, samplePawn.getCoordX());
        assertEquals(1, samplePawn.getCoordY());
    }

    @Test
    public void verifyArtemis() {

        testSample = new ExtraContext(Gods.ARTEMIS);
        Pawn samplePawn = testPlayer2.getPawnByNumber(2);
        Cell sampleOldCell = testBoard.getGrid()[3][3];
        samplePawn.setOldMove(sampleOldCell.getCoord());

        List<Cell> sample = new ArrayList<>();
        sample.add(testBoard.getGrid()[2][2]);
        sample.add(testBoard.getGrid()[2][3]);
        sample.add(testBoard.getGrid()[3][1]);
        sample.add(testBoard.getGrid()[4][1]);
        sample.add(testBoard.getGrid()[4][2]);
        sample.add(testBoard.getGrid()[4][3]);

        testCheckMethod(testSample, samplePawn, sample);

        testSample.execAction(new Coord(3,1), samplePawn, testModel, true);
        assertEquals(3, samplePawn.getCoordX());
        assertEquals(1, samplePawn.getCoordY());

        Cell cell = GetCell.getCellAdapter(new Coord(3,1), testBoard);
        assertEquals(samplePawn, cell.getOccupied());
    }

    @Test
    public void verifyDemeter() {

        testSample = new ExtraContext(Gods.DEMETER);
        Pawn samplePawn = testPlayer2.getPawnByNumber(1);
        Cell sampleOldCell = testBoard.getGrid()[2][1];
        samplePawn.setOldBuild(sampleOldCell.getCoord());

        List<Cell> sample = new ArrayList<>();
        sample.add(testBoard.getGrid()[3][1]);

        testCheckMethod(testSample, samplePawn, sample);

        samplePawn.setOldBuild(new Coord(3,1));
        List<Cell> sample2 = new ArrayList<>();

        testCheckMethod(testSample,samplePawn, sample2);

        testSample.execAction(new Coord(3,1), samplePawn, testModel, true);
        Cell cell = GetCell.getCellAdapter(new Coord(3,1), testBoard);
        assertEquals(3, cell.getFloor());
        assertFalse(cell.isRoof());
    }

    @Test
    public void verifyHephaestus() {

        testSample = new ExtraContext(Gods.HEPHAESTUS);
        Pawn samplePawn = testPlayer1.getPawnByNumber(2);
        Cell sampleOldCell = testBoard.getGrid()[2][2];
        samplePawn.setOldBuild(sampleOldCell.getCoord());

        List<Cell> sample = new ArrayList<>();
        sample.add(testBoard.getGrid()[2][2]);

        testCheckMethod(testSample, samplePawn, sample);

        testBoard.getGrid()[2][2].setFloor(3);
        List<Cell> sample2 = new ArrayList<>();

        testCheckMethod(testSample, samplePawn, sample2);

        testBoard.getGrid()[2][2].setFloor(0);

        testSample.execAction(new Coord(2,2), samplePawn, testModel, true);
        Cell cell = GetCell.getCellAdapter(new Coord(2,2), testBoard);
        assertEquals(1, cell.getFloor());
        assertFalse(cell.isRoof());


        testSample.execAction(new Coord(2,2), samplePawn, testModel, true);
        assertEquals(2, cell.getFloor());
        assertFalse(cell.isRoof());

    }

    @Test
    public void verifyPrometheus() {

        testSample = new ExtraContext(Gods.PROMETHEUS);
        Pawn samplePawn = testPlayer1.getPawnByNumber(1);

        List<Cell> sample = new ArrayList<>();
        sample.add(testBoard.getGrid()[0][0]);
        sample.add(testBoard.getGrid()[0][1]);
        sample.add(testBoard.getGrid()[0][2]);
        sample.add(testBoard.getGrid()[2][2]);

        testCheckMethod(testSample, samplePawn, sample);

        testSample.execAction(new Coord(2,2), samplePawn, testModel, true);
        Cell cell = GetCell.getCellAdapter(new Coord(2,2), testBoard);
        assertEquals(1, cell.getFloor());
        assertFalse(cell.isRoof());
    }

    private void testCheckMethod(ExtraContext context, Pawn pawn, List<Cell> sample){

        List<Cell> test = context.extraRequest(pawn, testBoard);

        assertEquals(sample.size(), test.size());
        for (Cell cell : test){
            assertTrue(sample.contains(cell));
        }

    }
}