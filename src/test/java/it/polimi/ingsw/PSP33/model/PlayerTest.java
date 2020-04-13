package it.polimi.ingsw.PSP33.model;

import it.polimi.ingsw.PSP33.model.Player;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;

public class PlayerTest {

    Player player;

    @Before
    public void setUp() {
        player = new Player("test", Color.BLACK);
    } //FIXME

    @After
    public void tearDown() {
        player = null;
    }

    @Test
    public void initPlayer_correctInput_correctOutput() {
        //FIXME
        Assert.assertEquals(player.getName(), "test");
        Assert.assertEquals(player.getColor(), Color.BLACK);
    }

    @Test
    public void setCard_correctInput_correctOutput() {

        this.player.setCard(null); //FIXME
        Assert.assertEquals(player.getCard(), 1);
    }
}
