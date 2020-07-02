package it.polimi.ingsw.PSP33.controller.rules.gods.strategy.win;

import it.polimi.ingsw.PSP33.model.*;
import it.polimi.ingsw.PSP33.utils.enums.Color;
import it.polimi.ingsw.PSP33.utils.enums.Gods;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class WinContextTest {

    private static Model testModel;
    private static Board testBoard;
    private static Player testPlayer1;
    private static Player testPlayer2;

    private WinContext winContext;

   /*
      NOTE! use this legend as a reference for the diffent test cases

      legend:
      * -> roof = true
      0,...,3 -> floor number
      p -> pawn position player1
      e -> pawn position player2

      -> Graphical rappresentation of the board state: (using matrix standard refence with the upper left stating as a [0,0] coordinates)
                 _______ _______ _______ _______ _______
                |       |       |  p    |       |       |
                |   0   |   1   |   2   |   0   |   0   |
                |_______|_______|_______|_______|_______|
                | *     |       |   *   |       |       |
                |   0   |   1   |   3   |   0   |   0   |
                |_______|_______|_______|_______|_______|
                | e     |       |  e    |       |       |
                |   0   |   3   |   0   |   0   |   0   |
                |_______|_______|_______|_______|_______|
                |   *   |  p    |       |       |       |
                |   0   |   2   |   2   |   0   |   0   |
                |_______|_______|_______|_______|_______|
                |       |       |       |       |       |
                |   0   |   0   |   0   |   0   |   0   |
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
        testBoard.getGrid()[0][2].setFloor(2);
        testBoard.getGrid()[1][1].setFloor(1);
        testBoard.getGrid()[1][2].setFloor(3);
        testBoard.getGrid()[2][1].setFloor(3);
        testBoard.getGrid()[3][1].setFloor(2);
        testBoard.getGrid()[3][2].setFloor(2);

        testBoard.getGrid()[1][0].setRoof(true);
        testBoard.getGrid()[1][2].setRoof(true);
        testBoard.getGrid()[3][0].setRoof(true);


        testPlayer1.getPawns()[0].setCoords(0,2);
        testBoard.getGrid()[0][2].setOccupied(testPlayer1.getPawns()[0]);

        testPlayer1.getPawns()[1].setCoords(3,1);
        testBoard.getGrid()[3][1].setOccupied(testPlayer1.getPawns()[1]);

        testPlayer2.getPawns()[0].setCoords(2,0);
        testBoard.getGrid()[2][0].setOccupied(testPlayer2.getPawns()[0]);

        testPlayer2.getPawns()[1].setCoords(2,2);
        testBoard.getGrid()[2][2].setOccupied(testPlayer2.getPawns()[1]);

    }

    @After
    public void tearDown(){
        winContext = null;
    }

    @Test
    public void verifyBasic() {

        winContext = new WinContext(Gods.NOGOD);
        Pawn pawn1 = testPlayer1.getPawnByNumber(1);
        Cell cell1 = testBoard.getGrid()[0][1];

        assertFalse(winContext.checkWinCondition(testBoard, pawn1, cell1));

        Pawn pawn2 = testPlayer2.getPawnByNumber(2);
        Cell cell2 = testBoard.getGrid()[2][1];

        assertFalse(winContext.checkWinCondition(testBoard, pawn2, cell2));

        Pawn pawn3 = testPlayer1.getPawnByNumber(2);
        Cell cell3 = testBoard.getGrid()[2][1];

        assertTrue(winContext.checkWinCondition(testBoard, pawn3, cell3));
    }

    @Test
    public void verifyPan() {
        winContext = new WinContext(Gods.PAN);
        Pawn pawn1 = testPlayer1.getPawnByNumber(1);
        Cell cell1 = testBoard.getGrid()[0][3];

        assertTrue(winContext.checkWinCondition(testBoard, pawn1, cell1));

        Pawn pawn2 = testPlayer2.getPawnByNumber(2);
        Cell cell2 = testBoard.getGrid()[2][1];

        assertFalse(winContext.checkWinCondition(testBoard, pawn2, cell2));

        Pawn pawn3 = testPlayer1.getPawnByNumber(2);
        Cell cell3 = testBoard.getGrid()[2][1];

        assertTrue(winContext.checkWinCondition(testBoard, pawn3, cell3));
    }
}