package model;

import org.junit.*;
import org.junit.Assert;

public class BoardTest {

    Board board = null;

    @Before
    public void setUp() {
        this.board = new Board();
    }

    @After
    public void tearDown() {
        this.board = null;
    }

    @Test
    public void initBoard() {

    }
}
