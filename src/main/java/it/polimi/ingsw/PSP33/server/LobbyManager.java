package it.polimi.ingsw.PSP33.server;

import java.util.HashMap;
import java.util.Map;


/**
 * Class that manages multiple lobbies
 */
class LobbyManager {

    /**
     * Instance of the class to make it singleton
     */
    private static LobbyManager instance = new LobbyManager();

    /**
     * Lobby ID
     */
    private static int lobbyID;

    /**
     * Map of lobbies with lobbyID used as key
     */
    private static Map<Integer, Lobby> lobbies;

    private LobbyManager() {
        lobbyID = 0;
        lobbies = new HashMap<>();
    }

    public static LobbyManager getInstance() {
        return instance;
    }

    public static Map<Integer, Lobby> getLobbies() {
        return lobbies;
    }

    public static Map<Integer, String> getLobbyMap() {
        Map<Integer, String> lobbyMap = new HashMap<>();

        for(Lobby lobby : getLobbies().values()) {
            lobbyMap.put(lobby.getLobbyID(), lobby.toString());
        }

        return lobbyMap;
    }

    public synchronized static void addLobby(Lobby lobby) {
        lobbies.put(lobby.getLobbyID(), lobby);
    }

    public synchronized static void removeLobby(Lobby lobby) {
        lobbies.remove(lobby.getLobbyID());
    }

    public synchronized static Lobby newLobby(int numberOfPlayers) {
        lobbyID++;

        Lobby lobby = new Lobby(lobbyID, numberOfPlayers);
        addLobby(lobby);

        Thread thread = new Thread(lobby);
        thread.start();

        return lobby;
    }

    public synchronized static boolean checkLobbyList(int lobbyID) {
        return lobbies.containsKey(lobbyID) && lobbies.get(lobbyID).checkSize();
    }

    public synchronized static Lobby getLobbyByID(int lobbyID) {
        return lobbies.get(lobbyID);
    }
}
