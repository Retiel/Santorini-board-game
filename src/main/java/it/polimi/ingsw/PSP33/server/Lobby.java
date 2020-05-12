package it.polimi.ingsw.PSP33.server;

import it.polimi.ingsw.PSP33.utils.enums.Color;

import java.io.IOException;
import java.util.*;

/**
 * Lobby class used to save data from clients of the same lobby
 */
public class Lobby implements Runnable {

    /**
     * Lobby ID used for debug
     */
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
     * Map to check if all clients are ready
     */
    private final Map<ClientHandler, Boolean> readyMap;


    public Lobby(int lobbyID, int numberOfPlayers) {
        this.lobbyID = lobbyID;
        this.numberOfPlayers = numberOfPlayers;
        this.clientHandlers = new ArrayList<>();
        this.clientNames = new ArrayList<>();
        this.colorList = new ArrayList<>();
        this.readyMap = new HashMap<>();

        //Fill the list of available colors
        fillColorList();
    }

    @Override
    public void run() {
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        startGame();
    }

    public int getLobbyID() {
        return lobbyID;
    }

    /**
     * Method to get the list of client handlers
     *
     * @return list of client handlers
     */
    public synchronized List<ClientHandler> getClientHandlers() {
        return clientHandlers;
    }

    public synchronized void addClient(ClientHandler clientHandler) {
        clientHandlers.add(clientHandler);
        readyMap.put(clientHandler, false);

        if(clientHandlers.size() == numberOfPlayers) {
            LobbyManager.removeLobby(this);
        }
    }

    public synchronized boolean checkSize() {
        return clientHandlers.size() < numberOfPlayers;
    }

    /**
     * Method to get the number of players
     *
     * @return number of players
     */
    public int getNumberOfPlayers() {
        return numberOfPlayers;
    }

    /**
     * Check if the name already exists within the list of clients' names
     * @param name selected name
     *
     * @return true if the name to check is in the list of all names
     */
    public synchronized boolean checkName(String name) {
        return clientNames.contains(name);
    }

    /**
     * Method to add a new client's name to the list of used names
     * @param name client's name
     */
    public synchronized void addName(String name) {
        clientNames.add(name);
    }

    /**
     * Method to fill the list of available colors
     */
    public void fillColorList() {
        colorList.addAll(Arrays.asList(Color.values()));
    }

    /**
     * Method to print the list of available colors
     *
     * @return string representation of the list of available colors
     */
    public synchronized String printColorList() {

        StringBuilder stringBuilder = new StringBuilder();

        for (Color color : colorList) {
            stringBuilder
                    .append(Color.getIndex(color))
                    .append(". ")
                    .append(color.name())
                    .append("\n");
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        return stringBuilder.toString();
    }

    /**
     * Method to check if a color is in the list of available colors
     * @param color color to check
     *
     * @return true if the color to check is in the list of available colors
     */
    public synchronized boolean checkColor(Color color) {
        return colorList.contains(color);
    }

    /**
     * Method to remove a selected color from the list of available colors and to notify the game handler
     * @param color selected color
     */
    public synchronized void removeColor(Color color) {
        colorList.remove(color);
    }

    public synchronized void setClientReady(ClientHandler clientHandler) {
        readyMap.replace(clientHandler, true);

        if (checkClientsReady()) {
            synchronized (this) {
                notify();
            }
        }
    }

    public boolean checkClientsReady() {
        if(clientHandlers.size() == numberOfPlayers) {
            for (Boolean b : readyMap.values()) {
                if (!b) {
                    return false;
                }
            }
            return true;
        } else {
            return false;
        }
    }

    public synchronized void removeClient(ClientHandler clientHandler) {
        readyMap.remove(clientHandler);
        clientHandlers.remove(clientHandler);

        if(clientHandler.getClientColor() != null) {
            clientNames.remove(clientHandler.getClientName());
        }

        if(!clientHandler.getClientName().equals("")) {
            colorList.add(clientHandler.getClientColor());
        }

        if(clientHandlers.size() == numberOfPlayers - 1) {
            LobbyManager.addLobby(this);
        }

        if(clientHandlers.size() == 0) {
            LobbyManager.removeLobby(this);
        }
    }

    /**
     * Method to start a new game for this lobby
     */
    public void startGame() {
        for (ClientHandler clientHandler : clientHandlers) {
            try {
                clientHandler.requestReady();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        GameHandler gameHandler = new GameHandler(this);

        for(ClientHandler clientHandler : clientHandlers) {
            clientHandler.addListener(gameHandler);
        }

        gameHandler.startGame();

        //Debug
        System.out.println("DEBUG_" + lobbyID + ": set game handler over");
    }
}
