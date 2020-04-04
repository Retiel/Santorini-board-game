package it.polimi.ingsw.PSP33.controller.tools;

import org.junit.*;

public class ToolsTest {

    private static Tools testSample;

    @BeforeClass
    public static void beforeClass(){
        testSample =  Tools.getInstance();

        Assert.assertEquals(testSample,Tools.getInstance());
    }

    @AfterClass
    public static void afterClass(){
        testSample =  null;
        Assert.assertNotNull(Tools.getInstance());
    }

    @Test
    public void getAdjacentCells() {

    }

    @Test
    public void areAdiacent() {

    }
}