package it.polimi.ingsw.PSP33.model;

import it.polimi.ingsw.PSP33.utils.patterns.observable.Observable;
import it.polimi.ingsw.PSP33.message.client.ClientMessage;
import it.polimi.ingsw.PSP33.utils.Phase;

import java.util.ArrayList;
import java.util.List;

/**
 * Model class that hold all information related to the state of the game
 */
public class Model extends Observable<ClientMessage> {

    /**
     * Board variable
     */
    private final Board board;

    /**
     * List of players
     */
    private List<Player> players;

    /**
     * Player on the curent turn
     */
    private Player currentPlayer;

    /**
     * Current phase of the turn
     */
    private Phase currentPhase;

    /**
     * Constructor of the class
     * @param players list of players playing
     */
    public Model(List<Player> players) {
        this.board = new Board(players);
        this.players = new ArrayList<>(players);
        this.currentPlayer = null;
        this.currentPhase = Phase.START;
    }

    /**
     * Method to get the board
     *
     * @return Board object
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Method to get the list of players
     *
     * @return list of players
     */
    public List<Player> getPlayers() {
        return new ArrayList<>(players);
    }

    /**
     * Method to set the list of players
     * @param players modified list of players
     */
    public void setPlayers(List<Player> players) {
        this.players = players;
    }

    /**
     * Method to get the player of the current turn
     *
     * @return Player object
     */
    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    /**
     * Method to set the player on the current turn
     * @param currentPlayer player playing in the turn
     */
    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    /**
     * Method to get the current phase of the turn
     *
     * @return Phase enum
     */
    public Phase getCurrentPhase() {
        return currentPhase;
    }

    /**
     * Method to set the current phase of the turn
     * @param currentPhase Phase of the turn
     */
    public void setCurrentPhase(Phase currentPhase) {
        this.currentPhase = currentPhase;
    }
}
