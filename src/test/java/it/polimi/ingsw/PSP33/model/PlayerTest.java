package it.polimi.ingsw.PSP33.model;

import it.polimi.ingsw.PSP33.utils.enums.Gods;
import org.junit.After;
import static org.junit.Assert.*;
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
        assertEquals(player.getName(), "test");
        assertEquals(player.getColor(), Color.BLACK);

        assertEquals(Color.BLACK, player.getPawnByNumber(1).getColor());
        assertEquals(Color.BLACK, player.getPawnByNumber(2).getColor());

        Pawn[] pawns = player.getPawns();

        assertEquals(2, pawns.length);
    }

    @Test
    public void setCard_correctInput_correctOutput() {

        this.player.setCard(new God(Gods.NOGOD, "seems legit all the way, maybe a god will arrive"));
        assertNotNull(player.getCard());
        assertEquals(Gods.NOGOD, player.getCard().getName());
        assertEquals("seems legit all the way, maybe a god will arrive", player.getCard().getDescription());
    }


}
