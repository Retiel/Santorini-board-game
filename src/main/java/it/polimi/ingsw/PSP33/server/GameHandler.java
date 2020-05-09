package it.polimi.ingsw.PSP33.server;

import com.google.gson.Gson;
import it.polimi.ingsw.PSP33.controller.Controller;
import it.polimi.ingsw.PSP33.events.toClient.MVEvent;
import it.polimi.ingsw.PSP33.events.toClient.data.DataGrid;
import it.polimi.ingsw.PSP33.events.toClient.setup.PossiblePlacement;
import it.polimi.ingsw.PSP33.events.toClient.turn.*;

import it.polimi.ingsw.PSP33.events.toServer.VCEvent;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Player;
import it.polimi.ingsw.PSP33.utils.patterns.observable.Listener;
import it.polimi.ingsw.PSP33.view.AbstractView;

import java.util.ArrayList;
import java.util.List;

public class GameHandler extends AbstractView implements Runnable, Listener {

    /**
     * Clients list
     */
    private final List<ClientHandler> clientHandlers;

    private ClientHandler currentClient;

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

        System.out.println("MVC setup complete.");

        for(ClientHandler clientHandler : clientHandlers) {
            synchronized (clientHandler) {
                try {
                    clientHandler.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }

        System.out.println("Game real start");
        //Start

    }

    //FIXME: deserialization
    public synchronized void didReceiveMessage(String json) {
        Gson gson = new Gson();
        VCEvent vcEvent = gson.fromJson(json, VCEvent.class);

        notifyObservers(vcEvent);
    }

    @Override
    public void update(MVEvent message) {
        message.accept(this);
    }

    @Override
    public void visit(DataGrid dataGrid) {

    }

    @Override
    public void visit(PossiblePlacement possiblePlacement) {

    }

    @Override
    public void visit(YouLose youLose) {

    }

    @Override
    public void visit(YouWin youWin) {

    }

    @Override
    public void visit(NewAction newAction) {

    }

    @Override
    public void visit(PossibleBuild possibleBuild) {

    }

    @Override
    public void visit(PossibleMove possibleMove) {

    }

    @Override
    public void visit(PossibleExtraAction possibleExtraAction) {

    }
}
