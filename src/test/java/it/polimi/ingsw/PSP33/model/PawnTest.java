package it.polimi.ingsw.PSP33.model;

import it.polimi.ingsw.PSP33.utils.Coord;
import org.junit.After;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class PawnTest {

    Pawn pawn;

    @Before
    public void setUp() {
        pawn = new Pawn(Color.BLACK, 1);
    }

    @After
    public void tearDown() {
        pawn = null;
    }

    @Test
    public void initPawn() {

        assertEquals(pawn.getColor(), Color.BLACK);
        assertEquals(pawn.getNumber(), 1);

        assertNull(pawn.getCoord());
        assertNull(pawn.getOldMove());
        assertNull(pawn.getOldBuild());
        assertNull(pawn.getOldExtra());
    }

    @Test
    public void setPosition_getPosition() {
        pawn.setCoords(1,2);

        assertEquals(1, pawn.getCoordX());
        assertEquals(2, pawn.getCoordY());
    }

    @Test
    public void setHistory_getHistory() {

        pawn.setOldMove(new Coord(1,2));
        pawn.setOldBuild(new Coord(1,2));
        pawn.setOldExtra(new Coord(1,2));

        assertNotNull(pawn.getOldMove());
        assertNotNull(pawn.getOldBuild());
        assertNotNull(pawn.getOldExtra());
    }
}
