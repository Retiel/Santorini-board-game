package it.polimi.ingsw.PSP33.server;

import it.polimi.ingsw.PSP33.utils.enums.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Lobby {

    //Debug
    private final int lobbyID;

    /**
     * List of client handlers of the lobby
     */
    private final List<ClientHandler> clientHandlers;

    /**
     * List of client names of the lobby
     */
    private final List<String> clientNames;

    /**
     * List of available colors for clients
     */
    private final List<Color> colorList;

    /**
     * Number of players
     */
    private int numberOfPlayers;

    /**
     * Volatile boolean to check if all player selections are over
     */
    private volatile boolean selectionOver;

    public Lobby(int lobbyID) {
        this.lobbyID = lobbyID;
        this.clientHandlers = new ArrayList<>();
        this.clientNames = new ArrayList<>();
        this.colorList = new ArrayList<>();
        this.numberOfPlayers = 0;
        this.selectionOver = false;

        fillColorList();
    }

    public List<ClientHandler> getClientHandlers() {
        return clientHandlers;
    }

    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    public List<Color> getColorList() {
        return colorList;
    }

    boolean isSelectionOver() {

        for(ClientHandler clientHandler : clientHandlers) {
            if(clientHandler.getClientName().equals("") || clientHandler.getClientColor() == null) {
                selectionOver = false;
                return selectionOver;
            }
        }

        selectionOver = true;
        return selectionOver;
    }

    public void addName(String name) {
        synchronized (clientNames) {
            clientNames.add(name);
        }
    }

    /**
     * Check if the name already exists within the list of clients' names
     * @param name client's name
     *
     * @return true if the client's name isn't in the list of all names
     */
    public boolean checkName(String name) {
        return clientNames.contains(name);
    }

    public void removeColor(Color color) {
        synchronized (colorList) {
            colorList.remove(color);
        }
    }

    public boolean checkColor(Color color) {
        synchronized (colorList) {
            for (Color color1 : colorList) {
                if (color.equals(color1)) {
                    return true;
                }
            }

            return false;
        }
    }

    public void fillColorList() {
        colorList.addAll(Arrays.asList(Color.values()));
    }

    public String printColorList() {

        StringBuilder stringBuilder = new StringBuilder();

        synchronized (colorList) {
            for (Color color : colorList) {
                stringBuilder
                        .append(Color.getIndex(color))
                        .append(". ")
                        .append(color.name())
                        .append("\n");
            }

            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }

        return stringBuilder.toString();
    }

    public void startGame() {

        GameHandler gameHandler = new GameHandler(clientHandlers);

        Thread thread = new Thread(gameHandler, "game_handler_" + lobbyID);
        thread.start();
    }
}

