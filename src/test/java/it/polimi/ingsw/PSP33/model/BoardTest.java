package it.polimi.ingsw.PSP33.model;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class BoardTest {

    private Board board;

    private ArrayList<Player> players;



    @Before
    public void setUp() {
        players = new ArrayList<>();
    }

    @After
    public void tearDown() {
        board = null;
    }

    @Test
    public void setPawns_correctInput_correctOutput(){
        //TODO
    }

    @Test
    public void initBoard_twoPlayers() {
        //TODO
    }

    @Test
    public void initBoard_threePlayers() {
        //TODO
    }
}
