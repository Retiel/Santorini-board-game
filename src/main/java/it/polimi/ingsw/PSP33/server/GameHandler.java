package it.polimi.ingsw.PSP33.server;

import it.polimi.ingsw.PSP33.controller.Controller;
import it.polimi.ingsw.PSP33.events.toClient.MVEvent;
import it.polimi.ingsw.PSP33.events.toClient.turn.NewAction;
import it.polimi.ingsw.PSP33.events.toServer.VCEvent;
import it.polimi.ingsw.PSP33.events.toServer.turn.NewTurn;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Player;
import it.polimi.ingsw.PSP33.utils.patterns.observable.Observable;
import it.polimi.ingsw.PSP33.utils.patterns.observable.Observer;

import java.io.IOException;
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

        System.out.println("finished set MVC");

        ClientHandler clientHandler = clientHandlers.get(1);
        try{
            clientHandler.sendMessage(new NewTurn());
        }catch (IOException e){
            System.out.println("Error sendMessage: " + e);
        }

        try{
            MVEvent mvEvent = clientHandler.readMessage();
            NewAction newAction = (NewAction) mvEvent;
            System.out.println(newAction.isMove());
        }catch (Exception e){
            System.out.println("Error sendMessage: " + e);
        }


    }

    @Override
    public void update(MVEvent message) {
        System.out.println("Eminem - Berzerk (Official Music Video) (Explicit)");
    }
}
