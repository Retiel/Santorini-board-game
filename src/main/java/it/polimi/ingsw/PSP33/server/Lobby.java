package it.polimi.ingsw.PSP33.server;

import it.polimi.ingsw.PSP33.utils.enums.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

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
     * Boolean list to check if all clients are ready
     */
    private final List<Boolean> areClientsReady;


    public Lobby(int lobbyID, int numberOfPlayers) {
        this.lobbyID = lobbyID;
        this.numberOfPlayers = numberOfPlayers;
        this.clientHandlers = new ArrayList<>();
        this.clientNames = new ArrayList<>();
        this.colorList = new ArrayList<>();
        this.areClientsReady = new ArrayList<>();

        //Fill the list of available colors
        fillColorList();
        setNumberOfPlayers();
    }

    public int getLobbyID() {
        return lobbyID;
    }



    /**
     * Method to add a new client's name to the list of used names
     * @param name client's name
     */
    public synchronized void addName(String name) {
        clientNames.add(name);
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
     * Method to remove a selected color from the list of available colors and to notify the game handler
     * @param color selected color
     */
    public synchronized void removeColor(Color color) {
        synchronized (colorList) {
            colorList.remove(color);

            //Check if all clients have selected their colors
            if(colorList.size() == 3 - numberOfPlayers) {
                //Notify game handler
                colorList.notify();
            }
        }
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
     * Method to start a new game for this lobby
     */
    public void startGame() {

        GameHandler gameHandler = new GameHandler(this);

        Thread thread = new Thread(gameHandler, "GameHandler_" + lobbyID);
        thread.start();

        //Debug
        System.out.println("DEBUG_" + lobbyID + ": set game handler over");
    }

    /**
     * Method to get the list of client handlers
     *
     * @return list of client handlers
     */
    public List<ClientHandler> getClientHandlers() {
        return clientHandlers;
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
     * Method to set the number of players
     */
    public void setNumberOfPlayers() {

        for(int i = 0; i < numberOfPlayers; i++) {
            areClientsReady.add(false);
        }
    }


    public synchronized void setClientReady(ClientHandler clientHandler) {
        int index = clientHandlers.indexOf(clientHandler);
        areClientsReady.set(index, true);

        if (checkClientsReady()) {
            synchronized (this) {
                notify();
            }
        }
    }

    public boolean checkClientsReady() {
        for(Boolean b : areClientsReady) {
            if(!b) {
                return false;
            }
        }
        return true;
    }

    public List<Boolean> getAreClientsReady() {
        return areClientsReady;
    }


    public synchronized void removeClient(ClientHandler clientHandler) {
        areClientsReady.set(clientHandlers.indexOf(clientHandler), false);
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

    public synchronized void addClient(ClientHandler clientHandler) {

        clientHandlers.add(clientHandler);

        if(clientHandlers.size() == numberOfPlayers) {
            LobbyManager.removeLobby(this);
        }
    }

    public synchronized boolean checkSize() {
        return clientHandlers.size() < numberOfPlayers;
    }

    @Override
    public void run() {

        while (true) {
            synchronized (this) {
                try {
                    wait();
                    break;
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        startGame();
    }
}
