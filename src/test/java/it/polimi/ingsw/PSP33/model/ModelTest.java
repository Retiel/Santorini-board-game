package it.polimi.ingsw.PSP33.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import it.polimi.ingsw.PSP33.utils.Phase;

import java.awt.*;
import java.util.ArrayList;

public class ModelTest {

    Model model;

    ArrayList<Player> players;

    BoardTest boardTest = new BoardTest();

    @Before
    public void setUp() {
        players = new ArrayList<>();
        players.add(new Player("player1", Color.BLACK));
        players.add(new Player("player2", Color.WHITE));
    }

    @After
    public void tearDown() {
        model = null;
        players = null;
    }

    @Test
    public void initModel_twoPlayers() {

        model = new Model(players);

        Assert.assertNotNull(model.getBoard());
        Assert.assertEquals(model.getPlayers().size(), 2);
        Assert.assertNull(model.getCurrentPlayer());
        Assert.assertEquals(model.getCurrentPhase(), Phase.START);
    }

    @Test
    public void initModel_threePlayers() {

        players.add(new Player("player3", Color.GRAY));
        model = new Model(players);

        Assert.assertNotNull(model.getBoard());
        Assert.assertEquals(model.getPlayers().size(), 3);
        Assert.assertNull(model.getCurrentPlayer());
        Assert.assertEquals(model.getCurrentPhase(), Phase.START);
    }

    @Test
    public void setCurrentPhase_correctInput_correctOutput() {

        model = new Model(players);

        model.setCurrentPhase(Phase.END);
        Assert.assertEquals(model.getCurrentPhase(), Phase.END);
    }

    @Test
    public void setCurrentPlayer_correctInput_correctOutput() {

        model = new Model(players);

        model.setCurrentPlayer(players.get(0));
        Assert.assertNotNull(model.getCurrentPlayer());
    }
}
