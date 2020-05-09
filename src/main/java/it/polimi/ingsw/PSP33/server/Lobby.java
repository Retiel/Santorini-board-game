package it.polimi.ingsw.PSP33.server;

import it.polimi.ingsw.PSP33.utils.enums.Color;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Lobby class used to save data from clients of the same lobby
 */
public class Lobby {

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
     * Construcor of the class
     * @param lobbyID looby ID used for debug
     */
    public Lobby(int lobbyID) {
        this.lobbyID = lobbyID;
        this.clientHandlers = new ArrayList<>();
        this.clientNames = new ArrayList<>();
        this.colorList = new ArrayList<>();
        this.numberOfPlayers = 0;

        //Fill the list of available colors
        fillColorList();
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

        GameHandler gameHandler = new GameHandler(clientHandlers);

        //Debug
        System.out.println("Game_" + lobbyID + " starts.");

        Thread thread = new Thread(gameHandler, "game_handler_" + lobbyID);
        thread.start();
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
     * @param numberOfPlayers number of players
     */
    public void setNumberOfPlayers(int numberOfPlayers) {
        this.numberOfPlayers = numberOfPlayers;
    }

    /**
     * Method to get the list of available colors
     *
     * @return list of available colors
     */
    public List<Color> getColorList() {
        return colorList;
    }
}
