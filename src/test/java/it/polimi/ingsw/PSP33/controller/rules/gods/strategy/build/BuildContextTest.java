package it.polimi.ingsw.PSP33.controller.rules.gods.strategy.build;

import it.polimi.ingsw.PSP33.controller.rules.gods.strategy.move.MoveContext;
import it.polimi.ingsw.PSP33.controller.rules.tools.GetCell;
import it.polimi.ingsw.PSP33.model.*;
import it.polimi.ingsw.PSP33.utils.Coord;
import it.polimi.ingsw.PSP33.utils.enums.Gods;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

public class BuildContextTest {

    private static Model testModel;
    private static Board testBoard;
    private static Player testPlayer1;
    private static Player testPlayer2;

   private BuildContext testBuildContext;

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

    }

    @After
    public void tearDown(){
        testBuildContext = null;
    }

    @Test
    public void verifyBasic() {

        testBuildContext = new BuildContext(Gods.NOGOD);
        assertTrue(testBuildContext.checkBuild(testPlayer1.getPawnByNumber(1), testBoard).isEmpty());

        Pawn testPawnPlayer1 = testPlayer1.getPawnByNumber(1);

        List<Cell> sample1 = new ArrayList<>();

        testCheckMethod(testBuildContext, testPawnPlayer1, sample1);

        Cell cell = GetCell.getCellAdapter(new Coord(0,0), testBoard);
        testBuildContext.execBuild(0,0,false, testModel);
        assertEquals(1, cell.getFloor());

        testBuildContext.execBuild(0,0,true, testModel);
        assertEquals(2, cell.getFloor());
        assertFalse(cell.isRoof());
    }

    @Test
    public void verifyHephaestus() {
        testBuildContext = new BuildContext(Gods.HEPHAESTUS);

        Pawn pawn1 = testPlayer2.getPawnByNumber(1);

        List<Cell> sample1 = new ArrayList<>();
        sample1.add(testBoard.getGrid()[3][1]);

        testCheckMethod(testBuildContext, pawn1, sample1);

        Cell cell = GetCell.getCellAdapter(new Coord(3,1), testBoard);
        testBuildContext.execBuild(3,1,false, testModel);
        assertEquals(3, cell.getFloor());

        testBuildContext.execBuild(3,1,true, testModel);
        assertEquals(3, cell.getFloor());
        assertTrue(cell.isRoof());
    }

    @Test
    public void verifyDemeter() {
        testBuildContext = new BuildContext(Gods.DEMETER);

        Pawn pawn1 = testPlayer2.getPawnByNumber(2);

        List<Cell> sample1 = new ArrayList<>();
        sample1.add(testBoard.getGrid()[2][2]);
        sample1.add(testBoard.getGrid()[2][3]);
        sample1.add(testBoard.getGrid()[3][1]);
        sample1.add(testBoard.getGrid()[3][3]);
        sample1.add(testBoard.getGrid()[4][1]);
        sample1.add(testBoard.getGrid()[4][2]);
        sample1.add(testBoard.getGrid()[4][3]);

        testCheckMethod(testBuildContext, pawn1, sample1);

        Cell cell = GetCell.getCellAdapter(new Coord(0,0), testBoard);
        testBuildContext.execBuild(0,0,false, testModel);
        assertEquals(1, cell.getFloor());

        testBuildContext.execBuild(0,0,true, testModel);
        assertEquals(2, cell.getFloor());
    }

    @Test
    public void verifyAtlas() {
        testBuildContext = new BuildContext(Gods.ATLAS);

        Pawn pawn1 = testPlayer1.getPawnByNumber(1);
        Pawn pawn2 = testPlayer1.getPawnByNumber(2);

        List<Cell> sample1 = new ArrayList<>();
        sample1.add(testBoard.getGrid()[0][0]);
        sample1.add(testBoard.getGrid()[0][1]);
        sample1.add(testBoard.getGrid()[0][2]);
        sample1.add(testBoard.getGrid()[2][2]);

        List<Cell> sample2 = new ArrayList<>();
        sample2.add(testBoard.getGrid()[2][2]);
        sample2.add(testBoard.getGrid()[3][1]);

        testCheckMethod(testBuildContext, pawn1, sample1);
        testCheckMethod(testBuildContext, pawn2, sample2);


        Cell cell = GetCell.getCellAdapter(new Coord(0,0), testBoard);
        testBuildContext.execBuild(0,0,false, testModel);
        assertEquals(1, cell.getFloor());

        testBuildContext.execBuild(0,0,true, testModel);
        assertEquals(1, cell.getFloor());
        assertTrue(cell.isRoof());
    }

    private void testCheckMethod(BuildContext buildContext, Pawn pawn, List<Cell> sample){

        List<Cell> test = buildContext.checkBuild(pawn, testBoard);

        assertEquals(sample.size(), test.size());
        for (Cell cell : test){
            assertTrue(sample.contains(cell));
        }

    }
}