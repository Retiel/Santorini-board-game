package model;

import org.junit.*;
import org.junit.Assert;

import java.awt.*;

public class PlayerTest {

    Player player = null;

    @Test
    public void initPlayer_correctInput_correctOutput() {

        this.player = new Player("test", Color.BLACK);
        Assert.assertEquals(this.player.getName(), "test");
        Assert.assertEquals(this.player.getColor(), Color.BLACK);
    }
}
