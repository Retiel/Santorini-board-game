package it.polimi.ingsw.PSP33.model;

import it.polimi.ingsw.PSP33.events.to_client.MVEvent;
import it.polimi.ingsw.PSP33.events.to_client.setup.CurrentPlayer;
import it.polimi.ingsw.PSP33.utils.enums.Gods;
import it.polimi.ingsw.PSP33.utils.observable.Observable;

import java.util.ArrayList;
import java.util.List;

/**
 * Model class that holds all information related to the state of the game
 */
public class Model extends Observable<MVEvent> {

    /**
     * Board variable
     */
    private final Board board;

    /**
     * List of players
     */
    private List<Player> players;

    /**
     * Player on the current turn
     */
    private Player currentPlayer;

    /**
     * Pawn used by the current player
     */
    private Pawn currentPawn;

    /**
     * God's name used by the current player
     */
    private Gods currentGodName;

    /**
     * Constructor of the class
     * @param players list of players
     */
    public Model(List<Player> players) {
        this.board = new Board();
        this.players = new ArrayList<>(players);
        this.currentPlayer = null;
    }

    /**
     * Method to get the board
     *
     * @return Board class object
     */
    public Board getBoard() {
        return board;
    }

    /**
     * Method to get the list of players
     *
     * @return List of players clas object
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
     * @return Player class object
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
        notifyObservers(new CurrentPlayer(currentPlayer.getName()));
    }

    /**
     * Method to get the pawn used in the current turn by the player
     *
     * @return current pawn
     */
    public Pawn getCurrentPawn() {
        return currentPawn;
    }

    /**
     * Method to set the pawn used in the current turn by the player
     * @param currentPawn current pawn
     */
    public void setCurrentPawn(Pawn currentPawn) {
        this.currentPawn = currentPawn;
    }

    /**
     * Method to get the god's name used in the current turn by the player
     *
     * @return current god's name
     */
    public Gods getCurrentGodName() {
        return currentGodName;
    }

    /**
     * Method to set the god's name used in the current turn by the player
     * @param currentGodName current god's name
     */
    public void setCurrentGodName(Gods currentGodName) {
        this.currentGodName = currentGodName;
    }
}
