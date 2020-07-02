package it.polimi.ingsw.PSP33.model;

import it.polimi.ingsw.PSP33.utils.enums.Color;
import it.polimi.ingsw.PSP33.utils.enums.Gods;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class ModelTest {

    private Model model;

    private List<Player> players;

    @Before
    public void setUp() {
        players = new ArrayList<>();

        Player testPlayer1 = new Player("testPlayer1", Color.BLUE);
        Player testPlayer2 = new Player("testPlayer2", Color.GREEN);

        players.add(testPlayer1);
        players.add(testPlayer2);
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

        assertEquals(3, model.getPlayers().size());
        assertEquals(newEntry, model.getPlayers().get(2));

        testList = model.getPlayers();
        testList.remove(newEntry);
        model.setPlayers(testList);

        assertEquals(2, model.getPlayers().size());
        assertEquals(players, model.getPlayers());
    }

    @Test
    public void initModel_twoPlayers() {

        model = new Model(players);

        assertNotNull(model.getBoard());
        assertEquals(model.getPlayers().size(), 2);
        assertNull(model.getCurrentPlayer());
    }

    @Test
    public void initModel_threePlayers() {

        players.add(new Player("player3", Color.RED));
        model = new Model(players);

        assertNotNull(model.getBoard());
        assertEquals(model.getPlayers().size(), 3);
        assertNull(model.getCurrentPlayer());
    }


    @Test
    public void setCurrentPlayer_correctInput_correctOutput() {

        model = new Model(players);

        model.setCurrentPlayer(players.get(0));
        assertNotNull(model.getCurrentPlayer());
    }

    @Test
    public void setCurrentPawn_correctInput_correctOutput() {

        model = new Model(players);

        assertNull(model.getCurrentPawn());
        model.setCurrentPawn(new Pawn(Color.RED, 1));
        assertNotNull(model.getCurrentPawn());
    }

    @Test
    public void setCurrentGodName_correctInput_correctOutput() {

        model = new Model(players);

        model.setCurrentGodName(Gods.APOLLO);
        assertEquals(Gods.APOLLO, model.getCurrentGodName());
    }
}
