package it.polimi.ingsw.PSP33.controller.rules;

import it.polimi.ingsw.PSP33.events.toClient.data.DataModel;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Player;

/**
 * Class that manage everything in regards of change player turn and set up turn control
 */
public class TurnControl {

    private final Model model;
    public TurnControl(Model model) {
        this.model = model;
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
        model.notifyObservers(new DataModel(model));
    }

}
