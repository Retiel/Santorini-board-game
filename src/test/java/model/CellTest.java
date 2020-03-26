package model;

import org.junit.*;
import org.junit.Assert;

public class CellTest {

    Cell cell = null;

    @Before
    public void setUp() {
        this.cell = new Cell();
    }

    @After
    public void tearDown() {
        this.cell = null;
    }

    @Test
    public void initCell() {
        Assert.assertEquals(this.cell.getFloor(),0);
        Assert.assertFalse(this.cell.isRoof());
    }
}
