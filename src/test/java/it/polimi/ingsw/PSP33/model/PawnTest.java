package it.polimi.ingsw.PSP33.model;

import org.junit.After;
import org.junit.Assert;
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

        Assert.assertEquals(pawn.getColor(), Color.BLACK);
        Assert.assertEquals(pawn.getNumber(), 1);
    }

    /**
    @Test
    public void setCoordinates_correctInput_correctOutput() {

        pawn.setCoordX(0);
        pawn.setCoordY(0);
        Assert.assertEquals(pawn.getCoordX(), 0);
        Assert.assertEquals(pawn.getCoordY(), 0);
    }
    */
}
