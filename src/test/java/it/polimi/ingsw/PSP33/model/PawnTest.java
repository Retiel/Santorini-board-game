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
        pawn = new Pawn(Color.BLACK);
    }

    @After
    public void tearDown() {
        pawn = null;
    }

    @Test
    public void initPawn() {

        Assert.assertEquals(pawn.getColor(), Color.BLACK);
    }

    @Test
    public void setCoordinates_correctInput_correctOutput() {

        pawn.setCoords(0,0);
        Assert.assertEquals(pawn.getCoordX(), 0);
        Assert.assertEquals(pawn.getCoordY(), 0);
    }


    //FIXME: Exception for Pawn.setCoords()
    @Test (expected = ArrayIndexOutOfBoundsException.class)
    public void setCoordinates_wrongInput_throwsArrayIndexOutOfBoundsException() {

        pawn.setCoords(-1,6);
    }
}
