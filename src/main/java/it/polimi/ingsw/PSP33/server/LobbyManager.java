package it.polimi.ingsw.PSP33.server;

import java.net.Socket;
import java.util.List;
import java.util.Map;

public class LobbyManager {

    private List<Lobby> lobbyList;
    private List<Socket> clients;

    private Map<Lobby, List<Socket>> lobbyMap;
}
