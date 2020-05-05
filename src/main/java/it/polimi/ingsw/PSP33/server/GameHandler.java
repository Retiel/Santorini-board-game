package it.polimi.ingsw.PSP33.server;

import it.polimi.ingsw.PSP33.model.Player;

import java.util.ArrayList;
import java.util.List;

public class GameHandler implements Runnable {

    /**
     * Clients list
     */
    private final List<ClientHandler> clientHandlers;

    public GameHandler(List<ClientHandler> clientHandlers) {
        this.clientHandlers = clientHandlers;
    }

    @Override
    public void run() {

        List<Player> players = new ArrayList<>();

        for(ClientHandler clientHandler : clientHandlers) {
            players.add(new Player(clientHandler.getClientName(), clientHandler.getClientColor()));
        }

        //new Model
        //new Controller
        //new VirtualView
    }
}
