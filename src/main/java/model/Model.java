package model;

import utils.Phase;

import java.util.ArrayList;

/**
 * Model class
 */
public class Model {

    private Board board;
    private ArrayList<Player> players;
    private Player currentPlayer;
    private Phase phase;

    public Model(ArrayList<Player> players) {
        this.board = new Board();
        this.players = new ArrayList<Player>(players);
        this.currentPlayer = null;
        this.phase = Phase.START;
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

    public Phase getPhase() {
        return phase;
    }

    public void setPhase(Phase phase) {
        this.phase = phase;
    }
}
