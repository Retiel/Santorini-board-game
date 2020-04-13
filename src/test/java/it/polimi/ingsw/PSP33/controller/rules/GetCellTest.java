package it.polimi.ingsw.PSP33.controller.rules;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;
import it.polimi.ingsw.PSP33.model.Player;
import org.junit.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class GetCellTest {

    private static Board testBoard;
    private static Pawn testPawn;

    @BeforeClass
    public static void setUp(){

        List<Player> testPlayers = new ArrayList<>();
        Player testPlayer1 = new Player("testPlayer1", Color.BLACK);
        testPlayers.add(testPlayer1);

        testBoard = new Board(testPlayers);
/*
Graphical set up rappresentation
legend:
 * -> roof = true
 0,...,3 -> floor number
 P -> pawn position in the test
 e -> other pawn position
         _______ _______ _______ _______ _______
        |       |       |       |       |       |
        |   0   |   1   |   0   |   0   |   0   |
        |_______|_______|_______|_______|_______|
        | *     | p     |   *   |       |       |
        |   0   |   1   |   3   |   0   |   0   |
        |_______|_______|_______|_______|_______|
        | p     | p     |       |       |       |
        |   0   |   3   |   0   |   0   |   0   |
        |_______|_______|_______|_______|_______|
        |   *   |       | e     |       |       |
        |   0   |   2   |   2   |   0   |   0   |
        |_______|_______|_______|_______|_______|
        |       |       |       |       |       |
        |   0   |   1   |   0   |   0   |   0   |
        |_______|_______|_______|_______|_______|
 */

        testBoard.getGrid()[0][1].setFloor(1);
        testBoard.getGrid()[1][1].setFloor(1);
        testBoard.getGrid()[2][1].setFloor(3);
        testBoard.getGrid()[3][1].setFloor(2);
        testBoard.getGrid()[4][1].setFloor(1);

        testBoard.getGrid()[1][0].setRoof(true);

        testBoard.getGrid()[1][2].setFloor(3);
        testBoard.getGrid()[1][2].setRoof(true);

        testPlayer1.getPawn2().setCoords(3,2);
        testBoard.getGrid()[3][2].setOccupied(testPlayer1.getPawn2());

        testPawn = testPlayer1.getPawn1();

        GetCell testSample = GetCell.getInstance();
        Assert.assertEquals(testSample, GetCell.getInstance());
    }

    @Test
    public void testPosition_1_1() {

        getAdjacentCells_testPosition_1_1();
        getMovableCell_testPosition_1_1();
        getBuildableCell_testPosition_1_1();
    }

    /* test cases for adiacent method with changed position */
    private void getAdjacentCells_testPosition_1_1(){

        testPawn.setCoords(1,1);

        List<Cell> resultSample = GetCell.getAdjacentCells(testPawn, testBoard);

        Assert.assertEquals(8, resultSample.size());

        List<Cell> expectedList = new ArrayList<>();
        expectedList.add(testBoard.getGrid()[0][0]);
        expectedList.add(testBoard.getGrid()[0][1]);
        expectedList.add(testBoard.getGrid()[0][2]);
        expectedList.add(testBoard.getGrid()[1][0]);
        expectedList.add(testBoard.getGrid()[1][2]);
        expectedList.add(testBoard.getGrid()[2][0]);
        expectedList.add(testBoard.getGrid()[2][1]);
        expectedList.add(testBoard.getGrid()[2][2]);

        for(Cell sample : resultSample){
            Assert.assertTrue(expectedList.contains(sample));
        }
    }
    private void getAdjacentCells_testPosition_2_1(){

        testPawn.setCoords(2,1);

        List<Cell> resultSample2 = GetCell.getAdjacentCells(testPawn, testBoard);

        Assert.assertEquals(8, resultSample2.size());

        List<Cell> expectedList2 = new ArrayList<>();
        expectedList2.add(testBoard.getGrid()[1][0]);
        expectedList2.add(testBoard.getGrid()[1][1]);
        expectedList2.add(testBoard.getGrid()[1][2]);
        expectedList2.add(testBoard.getGrid()[2][0]);
        expectedList2.add(testBoard.getGrid()[2][2]);
        expectedList2.add(testBoard.getGrid()[3][0]);
        expectedList2.add(testBoard.getGrid()[3][1]);
        expectedList2.add(testBoard.getGrid()[3][2]);

        for(Cell sample : resultSample2){
            Assert.assertTrue(expectedList2.contains(sample));
        }
    }
    private void getAdjacentCells_testPosition_2_0(){

        testPawn.setCoords(2,0);

        List<Cell> resultSample3 = GetCell.getAdjacentCells(testPawn, testBoard);

        Assert.assertEquals(5, resultSample3.size());

        List<Cell> expectedList3 = new ArrayList<>();
        expectedList3.add(testBoard.getGrid()[1][0]);
        expectedList3.add(testBoard.getGrid()[1][1]);
        expectedList3.add(testBoard.getGrid()[2][1]);
        expectedList3.add(testBoard.getGrid()[3][0]);
        expectedList3.add(testBoard.getGrid()[3][1]);

        for(Cell sample : resultSample3){
            Assert.assertTrue(expectedList3.contains(sample));
        }
    }

    @Test
    public void testPosition_2_1() {

        getAdjacentCells_testPosition_2_1();
        getMovableCell_testPosition_2_1();
        getBuildableCell_testPosition_2_1();
    }

    /* test cases to get movable cell method with changed position */
    private void getMovableCell_testPosition_1_1(){

        testPawn.setCoords(1,1);

        List<Cell> resultSample = GetCell.getMovableCells(testPawn, testBoard);

        Assert.assertEquals(5, resultSample.size());

        List<Cell> expectedList = new ArrayList<>();
        expectedList.add(testBoard.getGrid()[0][0]);
        expectedList.add(testBoard.getGrid()[0][1]);
        expectedList.add(testBoard.getGrid()[0][2]);
        expectedList.add(testBoard.getGrid()[2][0]);
        expectedList.add(testBoard.getGrid()[2][2]);

        for(Cell sample : resultSample){
            Assert.assertTrue(expectedList.contains(sample));
        }
    }
    private void getMovableCell_testPosition_2_1(){

        testPawn.setCoords(2,1);

        List<Cell> resultSample2 = GetCell.getMovableCells(testPawn, testBoard);

        Assert.assertEquals(5, resultSample2.size());

        List<Cell> expectedList2 = new ArrayList<>();
        expectedList2.add(testBoard.getGrid()[1][1]);
        expectedList2.add(testBoard.getGrid()[2][0]);
        expectedList2.add(testBoard.getGrid()[2][2]);
        expectedList2.add(testBoard.getGrid()[3][0]);
        expectedList2.add(testBoard.getGrid()[3][1]);

        for(Cell sample : resultSample2){
            Assert.assertTrue(expectedList2.contains(sample));
        }
    }
    private void getMovableCell_testPosition_2_0(){

        testPawn.setCoords(2,0);

        List<Cell> resultSample3 = GetCell.getMovableCells(testPawn, testBoard);

        Assert.assertEquals(2, resultSample3.size());

        List<Cell> expectedList3 = new ArrayList<>();
        expectedList3.add(testBoard.getGrid()[1][1]);
        expectedList3.add(testBoard.getGrid()[3][0]);

        for(Cell sample : resultSample3){
            Assert.assertTrue(expectedList3.contains(sample));
        }
    }

    @Test
    public void testPosition_2_0() {

        getAdjacentCells_testPosition_2_0();
        getMovableCell_testPosition_2_0();
        getBuildableCell_testPosition_2_0();
    }

    /* test cases to get buildable cell with changed position */
    private void getBuildableCell_testPosition_1_1(){

        testPawn.setCoords(1,1);

        List<Cell> resultSample = GetCell.getBuildableCells(testPawn, testBoard);

        Assert.assertEquals(6, resultSample.size());

        List<Cell> expectedList = new ArrayList<>();
        expectedList.add(testBoard.getGrid()[0][0]);
        expectedList.add(testBoard.getGrid()[0][1]);
        expectedList.add(testBoard.getGrid()[0][2]);
        expectedList.add(testBoard.getGrid()[2][0]);
        expectedList.add(testBoard.getGrid()[2][1]);
        expectedList.add(testBoard.getGrid()[2][2]);

        for(Cell sample : resultSample){
            Assert.assertTrue(expectedList.contains(sample));
        }
    }
    private void getBuildableCell_testPosition_2_1(){

        testPawn.setCoords(2,1);

        List<Cell> resultSample = GetCell.getBuildableCells(testPawn, testBoard);

        Assert.assertEquals(5, resultSample.size());

        List<Cell> expectedList = new ArrayList<>();
        expectedList.add(testBoard.getGrid()[1][1]);
        expectedList.add(testBoard.getGrid()[2][0]);
        expectedList.add(testBoard.getGrid()[2][2]);
        expectedList.add(testBoard.getGrid()[3][0]);
        expectedList.add(testBoard.getGrid()[3][1]);

        for(Cell sample : resultSample){
            Assert.assertTrue(expectedList.contains(sample));
        }
    }
    private void getBuildableCell_testPosition_2_0(){

        testPawn.setCoords(2,0);

        List<Cell> resultSample = GetCell.getBuildableCells(testPawn, testBoard);

        Assert.assertEquals(4, resultSample.size());

        List<Cell> expectedList = new ArrayList<>();
        expectedList.add(testBoard.getGrid()[1][1]);
        expectedList.add(testBoard.getGrid()[2][1]);
        expectedList.add(testBoard.getGrid()[3][0]);
        expectedList.add(testBoard.getGrid()[3][1]);

        for(Cell sample : resultSample){
            Assert.assertTrue(expectedList.contains(sample));
        }
    }

    @Test
    public void areAdiacent() {

        Assert.assertTrue(GetCell.AreAdiacent(1,1,1,2));
        Assert.assertTrue(GetCell.AreAdiacent(1,1,0,0));
        Assert.assertTrue(GetCell.AreAdiacent(1,1,2,0));

        Assert.assertFalse(GetCell.AreAdiacent(1,1,3,0));
        Assert.assertFalse(GetCell.AreAdiacent(1,1,1,3));
        Assert.assertFalse(GetCell.AreAdiacent(1,1,1,1));
    }
}