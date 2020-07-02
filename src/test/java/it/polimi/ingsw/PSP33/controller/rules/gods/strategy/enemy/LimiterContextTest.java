package it.polimi.ingsw.PSP33.controller.rules.gods.strategy.enemy;

import it.polimi.ingsw.PSP33.controller.rules.tools.GetCell;
import it.polimi.ingsw.PSP33.model.*;
import it.polimi.ingsw.PSP33.utils.Coord;
import it.polimi.ingsw.PSP33.utils.enums.Color;
import it.polimi.ingsw.PSP33.utils.enums.Gods;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LimiterContextTest {

    private static Model testModel;
    private static Board testBoard;
    private static Player testPlayer1;
    private static Player testPlayer2;

    private LimiterContext limiterContext;

   /*
      NOTE! use this legend as a reference for the diffent test cases

      legend:
      * -> roof = true
      0,...,3 -> floor number
      p -> pawn position player1
      e -> pawn position player2

      -> Graphical rappresentation of the board state: (using matrix standard refence with the upper left stating as a [0,0] coordinates)
                 _______ _______ _______ _______ _______
                |       |       |   p   |       |       |
                |   0   |   1   |   0   |   0   |   2   |
                |_______|_______|_______|_______|_______|
                | *     |       |   *   |       |       |
                |   0   |   1   |   3   |   1   |   2   |
                |_______|_______|_______|_______|_______|
                | e     |       |       |       |  p    |
                |   0   |   3   |   0   |   0   |   0   |
                |_______|_______|_______|_______|_______|
                |   *   |       | e     |       |       |
                |   0   |   2   |   2   |   3   |   0   |
                |_______|_______|_______|_______|_______|
                |       |       |       |       |   *   |
                |   0   |   1   |   0   |   1   |   0   |
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
        testBoard.getGrid()[0][4].setFloor(2);

        testBoard.getGrid()[1][1].setFloor(1);
        testBoard.getGrid()[1][2].setFloor(3);
        testBoard.getGrid()[1][3].setFloor(1);
        testBoard.getGrid()[1][4].setFloor(2);

        testBoard.getGrid()[2][1].setFloor(3);

        testBoard.getGrid()[3][1].setFloor(2);
        testBoard.getGrid()[3][2].setFloor(2);
        testBoard.getGrid()[3][3].setFloor(3);

        testBoard.getGrid()[4][1].setFloor(1);
        testBoard.getGrid()[4][3].setFloor(1);

        testBoard.getGrid()[1][0].setRoof(true);
        testBoard.getGrid()[1][2].setRoof(true);
        testBoard.getGrid()[3][0].setRoof(true);
        testBoard.getGrid()[4][4].setRoof(true);


        testPlayer1.getPawns()[0].setCoords(0,2);
        testBoard.getGrid()[0][2].setOccupied(testPlayer1.getPawns()[0]);

        testPlayer1.getPawns()[1].setCoords(2,4);
        testBoard.getGrid()[2][4].setOccupied(testPlayer1.getPawns()[1]);

        testPlayer2.getPawns()[0].setCoords(2,0);
        testBoard.getGrid()[2][0].setOccupied(testPlayer2.getPawns()[0]);

        testPlayer2.getPawns()[1].setCoords(3,2);
        testBoard.getGrid()[3][2].setOccupied(testPlayer2.getPawns()[1]);

    }

    @Test
    public void verifyBasic() {

        limiterContext = new LimiterContext();
        Pawn pawn = testPlayer1.getPawnByNumber(1);

        limiterContext.activateGodLimiter(Gods.NOGOD, pawn, new Coord(0,0), testBoard);

        List<Cell> sample = new ArrayList<>();
        sample.add(testBoard.getGrid()[0][1]);
        sample.add(testBoard.getGrid()[0][3]);
        sample.add(testBoard.getGrid()[1][1]);
        sample.add(testBoard.getGrid()[1][3]);

        testLimitMethod(limiterContext,pawn, sample);

        limiterContext.resetGodTrigger(Gods.NOGOD, false);

        testLimitMethod(limiterContext,pawn, sample);
    }

    @Test
    public void verifyAthena() {

        limiterContext = new LimiterContext();
        Pawn pawn = testPlayer1.getPawnByNumber(1);
        Pawn pawn2 = testPlayer2.getPawnByNumber(2);

        limiterContext.activateGodLimiter(Gods.ATHENA, pawn, new Coord(0,1), testBoard);

        List<Cell> sample = new ArrayList<>();
        sample.add(testBoard.getGrid()[0][3]);

        List<Cell> sample2 = new ArrayList<>();
        sample2.add(testBoard.getGrid()[2][2]);
        sample2.add(testBoard.getGrid()[2][3]);
        sample2.add(testBoard.getGrid()[3][1]);
        sample2.add(testBoard.getGrid()[4][1]);
        sample2.add(testBoard.getGrid()[4][2]);
        sample2.add(testBoard.getGrid()[4][3]);

        testLimitMethod(limiterContext,pawn, sample);
        testLimitMethod(limiterContext,pawn2, sample2);

        limiterContext.resetGodTrigger(Gods.ATHENA, false);

        sample.add(testBoard.getGrid()[0][1]);
        sample.add(testBoard.getGrid()[1][1]);
        sample.add(testBoard.getGrid()[1][3]);

        sample2.add(testBoard.getGrid()[2][1]);
        sample2.add(testBoard.getGrid()[3][3]);

        testLimitMethod(limiterContext, pawn, sample);
        testLimitMethod(limiterContext, pawn2, sample2);


    }

    private void testLimitMethod(LimiterContext limiterContext, Pawn pawn, List<Cell> sample){

        List<Cell> test = GetCell.getMovableCells(pawn, testBoard);
         test = limiterContext.applyAllLimit(test, pawn, testBoard, Gods.PAN);

        assertEquals(sample.size(), test.size());
        for (Cell cell : test){
            assertTrue(sample.contains(cell));
        }

    }
}