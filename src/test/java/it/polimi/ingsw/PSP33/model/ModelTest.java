package it.polimi.ingsw.PSP33.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ModelTest {

    Model model;

    List<Player> players;

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
    public void setListPlayer_correctInput_correctOutput() {

        model = new Model(players);

        Player newEntry = new Player("testPlayer", Color.BLUE);

        List<Player> testList = new ArrayList<>(model.getPlayers());
        testList.add(newEntry);
        model.setPlayers(testList);

        Assert.assertEquals(3, model.getPlayers().size());
        Assert.assertEquals(newEntry, model.getPlayers().get(2));

        testList = model.getPlayers();
        testList.remove(newEntry);
        model.setPlayers(testList);

        Assert.assertEquals(2, model.getPlayers().size());
        Assert.assertEquals(players, model.getPlayers());
    }

    @Test
    public void initModel_twoPlayers() {

        model = new Model(players);

        Assert.assertNotNull(model.getBoard());
        Assert.assertEquals(model.getPlayers().size(), 2);
        Assert.assertNull(model.getCurrentPlayer());
    }

    @Test
    public void initModel_threePlayers() {

        players.add(new Player("player3", Color.GRAY));
        model = new Model(players);

        Assert.assertNotNull(model.getBoard());
        Assert.assertEquals(model.getPlayers().size(), 3);
        Assert.assertNull(model.getCurrentPlayer());
    }


    @Test
    public void setCurrentPlayer_correctInput_correctOutput() {

        model = new Model(players);

        model.setCurrentPlayer(players.get(0));
        Assert.assertNotNull(model.getCurrentPlayer());
    }
}
