package it.polimi.ingsw.PSP33.server;

import it.polimi.ingsw.PSP33.controller.Controller;
import it.polimi.ingsw.PSP33.events.toClient.MVEvent;
import it.polimi.ingsw.PSP33.events.toServer.VCEvent;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Player;
import it.polimi.ingsw.PSP33.utils.patterns.observable.Observable;
import it.polimi.ingsw.PSP33.utils.patterns.observable.Observer;

import java.util.ArrayList;
import java.util.List;

public class GameHandler extends Observable<VCEvent> implements Observer<MVEvent>, Runnable {

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

        Model model = new Model(players);
        Controller controller = new Controller(model);

        model.addObserver(this);
        this.addObserver(controller);

    }

    @Override
    public void update(MVEvent message) {

    }
}
