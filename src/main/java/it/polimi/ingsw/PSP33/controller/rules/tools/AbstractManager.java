package it.polimi.ingsw.PSP33.controller.rules.tools;

import it.polimi.ingsw.PSP33.events.toClient.MVEvent;
import it.polimi.ingsw.PSP33.events.toClient.data.DataCell;
import it.polimi.ingsw.PSP33.model.*;
import it.polimi.ingsw.PSP33.model.light_version.LightModel;
import it.polimi.ingsw.PSP33.utils.Coord;

import java.util.List;

/**
 * Class that manage everything in regards of change player turn and set up turn control
 */
public abstract class AbstractManager {

    private final Model model;

    private List<God> gods;
    private List<God> Allgods;

    public AbstractManager(Model model) {
        this.model = model;
        this.gods = null;
        this.Allgods = null;
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
        model.setCurrentPawn(null);
    }

    /**
     * Method to set the current pawn
     */
    public void setCurrentPawn(int pawn){
        getModel().setCurrentPawn(getModel().getCurrentPlayer().getPawnByNumber(pawn));
    }

    public List<God> getGods() {
        return gods;
    }

    public List<God> getAllgods() {
        return Allgods;
    }

    public void setGods(List<God> gods) {
        this.gods = gods;
    }

    public void setAllgods(List<God> allgods) {
        Allgods = allgods;
    }

    /* method used for testing */
    public void notifyView(MVEvent mvEvent){
        model.notifyObservers(mvEvent);
    }
}
