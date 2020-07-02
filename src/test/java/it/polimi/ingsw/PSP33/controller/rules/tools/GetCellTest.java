package it.polimi.ingsw.PSP33.controller.rules.tools;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;
import it.polimi.ingsw.PSP33.model.Player;
import it.polimi.ingsw.PSP33.utils.Coord;
import it.polimi.ingsw.PSP33.utils.enums.Color;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class GetCellTest {

    private static Board testBoard;
    private static Pawn testPawn;

    /*
        NOTE! use this legend as a reference for the diffent test cases

      legend:
      * -> roof = true
      0,...,3 -> floor number
      p -> pawn position in the test
      e -> other pawn position

      -> Graphical rappresentation of the board state:
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

    @BeforeClass
    public static void setUp(){

        List<Player> testPlayers = new ArrayList<>();
        Player testPlayer1 = new Player("testPlayer1", Color.BLUE);
        testPlayers.add(testPlayer1);

        testBoard = new Board();

        testBoard.getGrid()[0][1].setFloor(1);
        testBoard.getGrid()[1][1].setFloor(1);
        testBoard.getGrid()[2][1].setFloor(3);
        testBoard.getGrid()[3][1].setFloor(2);
        testBoard.getGrid()[4][1].setFloor(1);

        testBoard.getGrid()[1][0].setRoof(true);

        testBoard.getGrid()[1][2].setFloor(3);
        testBoard.getGrid()[1][2].setRoof(true);

        testPlayer1.getPawns()[1].setCoords(3,2);
        testBoard.getGrid()[3][2].setOccupied(testPlayer1.getPawns()[0]);

        testPawn = testPlayer1.getPawns()[0];

        GetCell testSample = GetCell.getInstance();assertEquals(testSample, GetCell.getInstance());
    }


    /* Test case simulating a situation described in position (1,1) */
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
        assertEquals(8, resultSample.size());

        List<Cell> expectedList = new ArrayList<>();
        expectedList.add(testBoard.getGrid()[0][0]);
        expectedList.add(testBoard.getGrid()[0][1]);
        expectedList.add(testBoard.getGrid()[0][2]);
        expectedList.add(testBoard.getGrid()[1][0]);
        expectedList.add(testBoard.getGrid()[1][2]);
        expectedList.add(testBoard.getGrid()[2][0]);
        expectedList.add(testBoard.getGrid()[2][1]);
        expectedList.add(testBoard.getGrid()[2][2]);

        for(Cell sample : resultSample){ assertTrue(expectedList.contains(sample));
        }
    }
    private void getAdjacentCells_testPosition_2_1(){

        testPawn.setCoords(2,1);

        List<Cell> resultSample2 = GetCell.getAdjacentCells(testPawn, testBoard);
        assertEquals(8, resultSample2.size());

        List<Cell> expectedList2 = new ArrayList<>();
        expectedList2.add(testBoard.getGrid()[1][0]);
        expectedList2.add(testBoard.getGrid()[1][1]);
        expectedList2.add(testBoard.getGrid()[1][2]);
        expectedList2.add(testBoard.getGrid()[2][0]);
        expectedList2.add(testBoard.getGrid()[2][2]);
        expectedList2.add(testBoard.getGrid()[3][0]);
        expectedList2.add(testBoard.getGrid()[3][1]);
        expectedList2.add(testBoard.getGrid()[3][2]);

        for(Cell sample : resultSample2){ assertTrue(expectedList2.contains(sample));
        }
    }
    private void getAdjacentCells_testPosition_2_0(){

        testPawn.setCoords(2,0);

        List<Cell> resultSample3 = GetCell.getAdjacentCells(testPawn, testBoard);
        assertEquals(5, resultSample3.size());

        List<Cell> expectedList3 = new ArrayList<>();
        expectedList3.add(testBoard.getGrid()[1][0]);
        expectedList3.add(testBoard.getGrid()[1][1]);
        expectedList3.add(testBoard.getGrid()[2][1]);
        expectedList3.add(testBoard.getGrid()[3][0]);
        expectedList3.add(testBoard.getGrid()[3][1]);

        for(Cell sample : resultSample3){ assertTrue(expectedList3.contains(sample));
        }
    }

    /* Test case simulating a situation described in position (2,1) */
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
        assertEquals(5, resultSample.size());

        List<Cell> expectedList = new ArrayList<>();
        expectedList.add(testBoard.getGrid()[0][0]);
        expectedList.add(testBoard.getGrid()[0][1]);
        expectedList.add(testBoard.getGrid()[0][2]);
        expectedList.add(testBoard.getGrid()[2][0]);
        expectedList.add(testBoard.getGrid()[2][2]);

        for(Cell sample : resultSample){ assertTrue(expectedList.contains(sample));
        }
    }
    private void getMovableCell_testPosition_2_1(){

        testPawn.setCoords(2,1);

        List<Cell> resultSample2 = GetCell.getMovableCells(testPawn, testBoard);
        assertEquals(5, resultSample2.size());

        List<Cell> expectedList2 = new ArrayList<>();
        expectedList2.add(testBoard.getGrid()[1][1]);
        expectedList2.add(testBoard.getGrid()[2][0]);
        expectedList2.add(testBoard.getGrid()[2][2]);
        expectedList2.add(testBoard.getGrid()[3][0]);
        expectedList2.add(testBoard.getGrid()[3][1]);

        for(Cell sample : resultSample2){ assertTrue(expectedList2.contains(sample));
        }
    }
    private void getMovableCell_testPosition_2_0(){

        testPawn.setCoords(2,0);

        List<Cell> resultSample3 = GetCell.getMovableCells(testPawn, testBoard);
        assertEquals(2, resultSample3.size());

        List<Cell> expectedList3 = new ArrayList<>();
        expectedList3.add(testBoard.getGrid()[1][1]);
        expectedList3.add(testBoard.getGrid()[3][0]);

        for(Cell sample : resultSample3){ assertTrue(expectedList3.contains(sample));
        }
    }

    /* Test case simulating a situation described in position (2,0) */
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
        assertEquals(6, resultSample.size());

        List<Cell> expectedList = new ArrayList<>();
        expectedList.add(testBoard.getGrid()[0][0]);
        expectedList.add(testBoard.getGrid()[0][1]);
        expectedList.add(testBoard.getGrid()[0][2]);
        expectedList.add(testBoard.getGrid()[2][0]);
        expectedList.add(testBoard.getGrid()[2][1]);
        expectedList.add(testBoard.getGrid()[2][2]);

        for(Cell sample : resultSample){ assertTrue(expectedList.contains(sample));
        }
    }
    private void getBuildableCell_testPosition_2_1(){

        testPawn.setCoords(2,1);

        List<Cell> resultSample = GetCell.getBuildableCells(testPawn, testBoard);
        assertEquals(5, resultSample.size());

        List<Cell> expectedList = new ArrayList<>();
        expectedList.add(testBoard.getGrid()[1][1]);
        expectedList.add(testBoard.getGrid()[2][0]);
        expectedList.add(testBoard.getGrid()[2][2]);
        expectedList.add(testBoard.getGrid()[3][0]);
        expectedList.add(testBoard.getGrid()[3][1]);

        for(Cell sample : resultSample){ assertTrue(expectedList.contains(sample));
        }
    }
    private void getBuildableCell_testPosition_2_0(){

        testPawn.setCoords(2,0);

        List<Cell> resultSample = GetCell.getBuildableCells(testPawn, testBoard);
        assertEquals(4, resultSample.size());

        List<Cell> expectedList = new ArrayList<>();
        expectedList.add(testBoard.getGrid()[1][1]);
        expectedList.add(testBoard.getGrid()[2][1]);
        expectedList.add(testBoard.getGrid()[3][0]);
        expectedList.add(testBoard.getGrid()[3][1]);

        for(Cell sample : resultSample){ assertTrue(expectedList.contains(sample));
        }
    }

    /* test case for method AreAdiacent */
    @Test
    public void areAdiacent() {
        assertTrue(GetCell.AreAdjacent(1,1,1,2));assertTrue(GetCell.AreAdjacent(1,1,0,0));assertTrue(GetCell.AreAdjacent(1,1,2,0));
        assertFalse(GetCell.AreAdjacent(1,1,3,0));assertFalse(GetCell.AreAdjacent(1,1,1,3));assertFalse(GetCell.AreAdjacent(1,1,1,1));
    }

    /* test case for method getListAdapte */
    @Test
    public void ConvertionListTest() {

        List<Cell> testList = new ArrayList<>();

        testList.add(testBoard.getGrid()[1][1]);
        testList.add(testBoard.getGrid()[1][1]);
        testList.add(testBoard.getGrid()[1][1]);

        List<Coord> test = GetCell.getListAdapter(testList);

        assertEquals(3, test.size());
        assertEquals(1, test.get(0).getX());
        assertEquals(1, test.get(0).getY());
    }

    /* test case for method getCellAdapter */
    @Test
    public void ConvertionTest() {

        Coord testCoord1 = new Coord(3,3);

        Cell test = GetCell.getCellAdapter(testCoord1, testBoard);

        assertEquals(testBoard.getGrid()[3][3], test);
    }

    /* test case for method getPlaceableCells */
    @Test
    public void SetUp_placeableCell() {

        List<Cell> cellList = GetCell.getPlaceCells(testBoard);
        List<Coord> testList = GetCell.getListAdapter(cellList);

        for (Coord coord : testList){
            assertTrue(cellList.contains(GetCell.getCellAdapter(coord, testBoard)));
        }


    }
}