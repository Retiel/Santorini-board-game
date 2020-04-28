package it.polimi.ingsw.PSP33.controller.rules;

import it.polimi.ingsw.PSP33.controller.rules.tools.BasicAction;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Player;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class BasicActionTest {

    private static Cell testCell;
    private static Player testPlayer1;
    private static Model testModel;

    @BeforeClass
    public static void beforeClass(){

        List<Player> testPlayers = new ArrayList<>();

        testPlayer1 = new Player("testPlayer1", Color.BLACK);
        Player testPlayer2 = new Player("testPlayer2", Color.GREEN);

        testPlayers.add(testPlayer1);
        testPlayers.add(testPlayer2);

        testModel = new Model(testPlayers);
        testCell = testModel.getBoard().getGrid()[1][1];


        Cell[][] testGrid = testModel.getBoard().getGrid();

        for (Cell[] rowCell : testGrid){
            for (Cell cell: rowCell){
                assertNull(cell.getOccupied());
                assertEquals(0, cell.getFloor());
                assertFalse(cell.isRoof());

            }
        }
    }

    @AfterClass
    public static void afterClass(){

        List<Player> testPlayers = new ArrayList<>();

        Player testPlayer3 = new Player("testPlayer3", Color.BLUE);
        Player testPlayer4 = new Player("testPlayer4", Color.YELLOW);

        testPlayers.add(testPlayer3);
        testPlayers.add(testPlayer4);

        testModel = new Model(testPlayers);

        Cell[][] testGrid = testModel.getBoard().getGrid();

        for (Cell[] rowCell : testGrid){
            for (Cell cell: rowCell){
                assertNull(cell.getOccupied());
                assertEquals(0, cell.getFloor());
                assertFalse(cell.isRoof());

            }
        }

    }

    @Test
    public void setPawnStartingPoint_correctInput_onePawnSet() {

        BasicAction.SetUpPawnPosition(testCell, testPlayer1.getPawns()[0]);
        allGridMoveCheck(1,1);
    }

    @Test
    public void movePawn() {

        Cell testOldCell = testModel.getBoard().getGrid()[1][1];
        Cell testNewCell = testModel.getBoard().getGrid()[1][3];
        BasicAction.MovePawn(testOldCell, testNewCell, testPlayer1.getPawns()[0]);

        allGridMoveCheck(1,3);

        testOldCell = testNewCell;
        testNewCell = testModel.getBoard().getGrid()[3][3];
        BasicAction.MovePawn(testOldCell, testNewCell, testPlayer1.getPawns()[0]);
        BasicAction.MovePawn(testOldCell, testNewCell, testPlayer1.getPawns()[0]);

        allGridMoveCheck(3,3);
    }

    private void allGridMoveCheck(int coordX, int coordY){

        Cell[][] testGrid = testModel.getBoard().getGrid();

        for (Cell[] rowCell : testGrid){
            for (Cell cell: rowCell){
                if (cell.getOccupied() != null){
                    assertEquals(cell.getOccupied(), testPlayer1.getPawns()[0]);
                    assertNotEquals(cell.getOccupied(), testPlayer1.getPawns()[1]);
                    assertEquals(coordX, cell.getCoordX());
                    assertEquals(coordY, cell.getCoordY());
                }
            }
        }
    }

    @Test
    public void buildPawn() {

        BasicAction.BuildBlock(testCell);
        allGridBuildCheck(1);

        BasicAction.BuildBlock(testCell);
        allGridBuildCheck(2);

        BasicAction.BuildBlock(testCell);
        BasicAction.BuildBlock(testCell);
        allGridBuildCheck(3);
        assertTrue(testCell.isRoof());
    }

    private void allGridBuildCheck(int floorLevel){

        Cell[][] testGrid = testModel.getBoard().getGrid();

        for (Cell[] rowCell : testGrid){
            for (Cell cell: rowCell){
                if (cell.getOccupied() != null){
                    assertEquals(1, cell.getCoordX());
                    assertEquals(1, cell.getCoordY());
                    assertEquals(floorLevel, cell.getFloor());
                }
            }
        }
    }
}