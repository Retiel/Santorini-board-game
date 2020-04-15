package it.polimi.ingsw.PSP33.controller.rules;

import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Player;

import java.util.Random;

public class TurnManager {

    private final Model model;

    public TurnManager(Model model) {
        this.model = model;
    }

    /**
     * Method to decide the the starting player
     */
    public void setStartingPlayer() {

        Random random = new Random();
        int numberOfPlayers = model.getPlayers().size();
        int randomInteger = random.nextInt(numberOfPlayers);

        model.setCurrentPlayer(model.getPlayers().get(randomInteger));
    }

    /**
     * Method to move the game to the next player turn
     */
    public void nextTurn() {
        Player current = model.getCurrentPlayer();

        int next = model.getPlayers().indexOf(current);
        next++;

        Player nextPlayer;
        if(next < model.getPlayers().size()) nextPlayer = model.getPlayers().get(next);
        else nextPlayer = model.getPlayers().get(0);

        model.setCurrentPlayer(nextPlayer);
    }
}
