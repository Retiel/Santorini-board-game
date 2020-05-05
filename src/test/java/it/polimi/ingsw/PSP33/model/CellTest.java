package it.polimi.ingsw.PSP33.model;

import it.polimi.ingsw.PSP33.utils.enums.Color;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;


public class CellTest {

    Cell cell;

    @Before
    public void setUp() {
        cell = new Cell(0,0);
    }

    @After
    public void tearDown() {
        cell = null;
    }

    @Test
    public void initCell_correctSetup() {

        assertEquals(cell.getFloor(),0);
        assertFalse(cell.isRoof());
        assertNull(cell.getOccupied());
        assertEquals(0, cell.getCoordX());
        assertEquals(0, cell.getCoordY());
        assertEquals(0, cell.getCoord().getX());
        assertEquals(0, cell.getCoord().getY());
    }

    @Test
    public void setFloor_correctInput_correctOutput() {

        cell.setFloor(1);
        assertEquals(cell.getFloor(), 1);

        cell.setFloor(4);
        assertEquals(cell.getFloor(), 4);
    }

    @Test
    public void setRoof_correctInput_correctOutput() {

        cell.setRoof(true);
        assertTrue(cell.isRoof());
    }

    @Test
    public void setOccupied_correctInput_correctOutput() {

        Pawn pawn = new Pawn(Color.RED, 2);

        cell.setOccupied(pawn);
        assertNotNull(cell.getOccupied());
        assertEquals(2, cell.getOccupied().getNumber());
        assertEquals(Color.RED, cell.getOccupied().getColor());

    }
}
