package it.polimi.ingsw.PSP33.controller.turnManager;

import it.polimi.ingsw.PSP33.model.Model;

import java.util.Random;

public class TurnManager implements TurnManagerInterface {

    private Model model;

    public TurnManager(Model model) {
        this.model = model;
    }

    @Override
    public void setStartingPlayer() {

        Random random = new Random();
        int numberOfPlayers = model.getPlayers().size();
        int randomInteger = random.nextInt(numberOfPlayers);

        model.setCurrentPlayer(model.getPlayers().get(randomInteger));
    }

    @Override
    public void nextPlayer() {

        if (model.getPlayers().get(model.getPlayers().size() - 1) == model.getCurrentPlayer()) {
            model.setCurrentPlayer(model.getPlayers().get(0));
        }else {
            model.setCurrentPlayer(model.getPlayers().get(model.getPlayers().indexOf(model.getCurrentPlayer()) + 1));

        }
    }

    @Override
    public void nextPhase() {

    }
}
