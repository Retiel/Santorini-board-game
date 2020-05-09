package it.polimi.ingsw.PSP33.controller.rules.tools;

import it.polimi.ingsw.PSP33.events.toClient.MVEvent;
import it.polimi.ingsw.PSP33.events.toClient.data.DataGrid;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Player;

/**
 * Class that manage everything in regards of change player turn and set up turn control
 */
public abstract class AbstractManager {

    private final Model model;

    public AbstractManager(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    public Board getBoard() {
        return model.getBoard();
    }

    /**
     * Method to move the game to the next player turn
     */
    public void nextTurn() {

        Player current = model.getCurrentPlayer();
        Player nextPlayer;
        int next = model.getPlayers().indexOf(current) + 1;

        if(next < model.getPlayers().size()) nextPlayer = model.getPlayers().get(next);
        else nextPlayer = model.getPlayers().get(0);

        model.setCurrentPlayer(nextPlayer);
    }

    /* method used for testing */
    public void notifyView(MVEvent mvEvent){
        model.notifyObservers(mvEvent);
    }
}
