package model;

import message.client.ClientMessage;
import utils.Observable;
import utils.Phase;

import java.util.ArrayList;

/**
 * Model class
 */
public class Model extends Observable<ClientMessage> {

    private Board board;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private Phase currentPhase;

    public Model(ArrayList<Player> players) {
        this.board = new Board();
        this.players = new ArrayList<>(players);
        this.currentPlayer = null;
        this.currentPhase = Phase.START;
    }

    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
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
