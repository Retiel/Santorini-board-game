package it.polimi.ingsw.PSP33.model;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class BoardTest {

    private Board board;

    @Before
    public void setUp() {
        board = new Board();
    }

    @After
    public void tearDown() {
        board = null;
    }

    @Test
    public void initBoards() {
        Cell[][] grid = board.getGrid();
        assertEquals(5, board.getSIZE());

        for (Cell[] rows : grid){
            for (Cell cell : rows){
                assertNotNull(cell);
                assertFalse(cell.isRoof());
                assertEquals(0, cell.getFloor());
                assertNull(cell.getOccupied());
            }
        }
    }
}
