package it.polimi.ingsw.PSP33.model;

import org.junit.*;


public class GodTest {

    private static God testSample;
    private static String name;
    private static String description;

    @BeforeClass
    public static void setUp() {

        name = "Tilt";
        description = "On steroid never bath with juice and cornflakes, neither swim in the bed with red light";

        testSample =  new God(name,description);
    }


    @AfterClass
    public static void tearDown() {
        testSample = null;
    }

    @Test
    public void test(){
        Assert.assertEquals(testSample.getName(),name);
        Assert.assertEquals(testSample.getDescription(),description);
    }
}