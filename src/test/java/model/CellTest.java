package model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class CellTest {

    Cell cell;

    @Before
    public void setUp() {
        cell = new Cell();
    }

    @After
    public void tearDown() {
        cell = null;
    }

    @Test
    public void initCell() {

        Assert.assertEquals(cell.getFloor(),0);
        Assert.assertFalse(cell.isRoof());
    }

    @Test
    public void setFloor_correctInput_correctOutput() {

        cell.setFloor(1);
        Assert.assertEquals(cell.getFloor(), 1);
    }

    @Test
    public void setRoof_correctInput_correctOutput() {

        cell.setRoof(true);
        Assert.assertTrue(cell.isRoof());
    }
}
