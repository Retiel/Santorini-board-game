package model;

import message.client.ClientMessage;
import utils.patterns.observable.Observable;
import utils.Phase;

import java.util.ArrayList;

/**
 * Model class
 */
public class Model extends Observable<ClientMessage> {

    private final Board board;
    private final ArrayList<Player> players;
    private Player currentPlayer;
    private Phase currentPhase;

    public Model(ArrayList<Player> players) {
        this.board = new Board(players);
        this.players = new ArrayList<>(players);
        this.currentPlayer = null;
        this.currentPhase = Phase.START;
    }

    public Board getBoard() {
        return board;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public Phase getCurrentPhase() {
        return currentPhase;
    }

    public void setCurrentPhase(Phase currentPhase) {
        this.currentPhase = currentPhase;
    }
}
