package it.polimi.ingsw.PSP33.controller.rules;

import it.polimi.ingsw.PSP33.controller.rules.tools.GetCell;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Player;
import it.polimi.ingsw.PSP33.utils.Coord;
import it.polimi.ingsw.PSP33.utils.enums.Color;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class SetUpManagerTest {

    private static SetUpManager testSample;
    private static Model testModel;

    @BeforeClass
    public static void beforeClass(){

        List<Player> testPlayers = new ArrayList<>();

        Player testPlayer1 = new Player("testPlayer1", Color.BLUE);
        Player testPlayer2 = new Player("testPlayer2", Color.GREEN);

        testPlayers.add(testPlayer1);
        testPlayers.add(testPlayer2);

        testModel = new Model(testPlayers);
        testSample = new SetUpManager(testModel);

        assertNull(testModel.getCurrentPlayer());
    }

    /* Test to simulate a setup turn */
    @Test
    public void SetUpTurn(){

        /* setting the starting player */
        testSample.setStartingPlayer();
        assertNotNull(testSample.getModel().getCurrentPlayer());

        /* asseting the placeable cell are all the board */
        List<Coord> testList = testSample.GetAvailablePlacement();
        List<Cell> sample = GetCell.getPlaceableCells(testModel.getBoard());

        for (Coord coord : testList){
            assertTrue(sample.contains(GetCell.getCellAdapter(coord, testModel.getBoard())));
        }

        /* asseting the placeable cell are all the board except cell (1,1) */

        assertEquals(0, testSample.getPawn());
        testSample.PlacePlayerPawn(1,1);
        testList = testSample.GetAvailablePlacement();

        for (Coord coord : testList){

            boolean test = sample.contains(GetCell.getCellAdapter(coord, testModel.getBoard()));

            if(coord.getX() == 1 && coord.getY() == 1) assertFalse(test);
            else assertTrue(test);
        }

        /* verifying that the checks return false */
        assertFalse(testSample.CheckEndTurn());
        assertFalse(testSample.CheckPawnSetUp());

        assertEquals(1, testSample.getPawn());
        testSample.PlacePlayerPawn(1,2);

        /* verifying that the checks return true (end turn) and false (end setup) */
        assertTrue(testSample.CheckEndTurn());
        assertFalse(testSample.CheckPawnSetUp());

        testSample.nextTurn();

        assertEquals(0, testSample.getPawn());
        testSample.PlacePlayerPawn(2,2);

        assertEquals(1, testSample.getPawn());
        testSample.PlacePlayerPawn(2,3);

        /* verifying that the checks return true*/
        assertTrue(testSample.CheckEndTurn());
        assertTrue(testSample.CheckPawnSetUp());

    }

}