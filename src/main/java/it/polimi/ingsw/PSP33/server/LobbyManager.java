package it.polimi.ingsw.PSP33.server;

import java.util.HashMap;
import java.util.Map;

class LobbyManager {

    private static LobbyManager instance = new LobbyManager();

    private static int lobbyID;
    private static Map<Integer, Lobby> lobbies;


    private LobbyManager() {
        lobbyID = 0;
        lobbies = new HashMap<>();
    }

    public static LobbyManager getInstance() {
        return instance;
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

    public synchronized static String printLobbyList() {
        StringBuilder stringBuilder = new StringBuilder();

        for(Lobby lobby : lobbies.values()) {
            stringBuilder
                    .append(lobby.getLobbyID())
                    .append(". ").append("< ")
                    .append(lobby.getClientHandlers().size())
                    .append(" / ")
                    .append(lobby.getNumberOfPlayers())
                    .append(" >\n");
        }
        if(!stringBuilder.toString().equals("")) {
            stringBuilder.deleteCharAt(stringBuilder.length() - 1);
        }

        return stringBuilder.toString();
    }

    public synchronized static boolean checkLobbyList(int lobbyID) {
        return lobbies.containsKey(lobbyID) && lobbies.get(lobbyID).checkSize();
    }

    public synchronized static Lobby getLobbyByID(int lobbyID) {
        return lobbies.get(lobbyID);
    }
}
