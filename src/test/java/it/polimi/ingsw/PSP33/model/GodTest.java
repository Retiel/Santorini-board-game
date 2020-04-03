package it.polimi.ingsw.PSP33.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class GodTest {

    private static God testSample;
    private static String name;
    private static String description;

    @Before
    public static void setUp() {

        name = "Tilt";
        description = "On steroid never bath with juice and cornflakes, neither swim in the bed with red light";

        testSample =  new God(name,description);
    }


    @After
    public static void tearDown() {
        testSample = null;
    }

    @Test
    public void test(){
        Assert.assertEquals(testSample.getGodName(),name);
        Assert.assertEquals(testSample.getDescription(),description);
    }
}