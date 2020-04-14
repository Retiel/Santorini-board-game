package it.polimi.ingsw.PSP33.model;

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
    }

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

        this.player.setCard(new God("fame", "seems legit all the way, maybe a god will arrive"));
        Assert.assertNotNull(player.getCard());
        Assert.assertEquals("fame", player.getCard().getName());
        Assert.assertEquals("seems legit all the way, maybe a god will arrive", player.getCard().getDescription());
    }
}
