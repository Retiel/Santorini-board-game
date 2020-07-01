package it.polimi.ingsw.PSP33.server;

import it.polimi.ingsw.PSP33.utils.enums.Color;

import java.util.*;

/**
 * Lobby class used to save data from clients of the same lobby
 */
public class Lobby implements Runnable {

    /**
     * Lobby ID
     */
    private final int lobbyID;

    /**
     * Boolean to check if the game has started
     */
    private boolean isGameStarted;

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

    /**
     * Constructor of the class
     * @param lobbyID lobby ID
     * @param numberOfPlayers number of players
     */
    public Lobby(int lobbyID, int numberOfPlayers) {
        this.lobbyID = lobbyID;
        this.isGameStarted = false;
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
        //Keeps waiting until all clients are ready
        synchronized (this) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        startGame();
    }

    /**
     * Method to get the lobby ID
     *
     * @return lobby ID used for debug
     */
    public int getLobbyID() {
        return lobbyID;
    }

    public boolean isGameStarted() {
        return isGameStarted;
    }

    /**
     * Method to get the list of client handlers
     *
     * @return list of client handlers
     */
    public synchronized List<ClientHandler> getClientHandlers() {
        return clientHandlers;
    }

    /**
     * Method to add a new client to the lobby
     * @param clientHandler client to be added to the lobby
     */
    public synchronized void addClient(ClientHandler clientHandler) {
        clientHandlers.add(clientHandler);
        readyMap.put(clientHandler, false);

        if(clientHandlers.size() == numberOfPlayers) {
            LobbyManager.removeLobby(this);
        }
    }

    /**
     * Method to remove one client from the lobby
     * @param clientHandler client handler to be removed
     */
    public synchronized void removeClient(ClientHandler clientHandler) {
        readyMap.remove(clientHandler);
        clientHandlers.remove(clientHandler);

        if(clientHandler.getClientColor() != null) {
            colorList.add(clientHandler.getClientColor());
        }

        if(!clientHandler.getClientName().equals("")) {
            clientNames.remove(clientHandler.getClientName());
        }

        if(clientHandlers.size() > 0 && clientHandlers.size() < numberOfPlayers && !isGameStarted) {
            LobbyManager.addLobby(this);
        }

        if(clientHandlers.size() == 0) {
            LobbyManager.removeLobby(this);
        }
    }

    /**
     * Method to check the size of client handlers
     *
     * @return true if the size of client handlers is less than the number of players
     */
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
     * Method to check if the name already exists within the list of clients' names
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

    public synchronized List<Color> getColorList() {
        return colorList;
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

    /**
     * Method to check if all clients are ready
     *
     * @return true if all values in readyMap are true
     */
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

    /**
     * Method to set one client to ready
     * @param clientHandler client to be set to ready
     */
    public synchronized void setClientReady(ClientHandler clientHandler) {
        readyMap.replace(clientHandler, true);

        if (checkClientsReady()) {
            synchronized (this) {
                notify();
            }
        }
    }

    /**
     * Method to start a new game for this lobby
     */
    public void startGame() {
        //Notify all clients
        for (ClientHandler clientHandler : clientHandlers) {
            clientHandler.setReady();
        }

        //GameHandler
        GameHandler gameHandler = new GameHandler(this);

        //Listener
        for(ClientHandler clientHandler : clientHandlers) {
            clientHandler.addListener(gameHandler);
        }

        //Debug
        System.out.println("Lobby_" + lobbyID + ": game handler done");

        //Starts the game
        isGameStarted = true;
        gameHandler.startGame();
    }

    @Override
    public String toString() {
        return "Lobby_" + lobbyID + " < " + getClientHandlers().size() + " / " + getNumberOfPlayers() + " >";
    }
}
