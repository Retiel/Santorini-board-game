package it.polimi.ingsw.PSP33.server;


import it.polimi.ingsw.PSP33.controller.Controller;
import it.polimi.ingsw.PSP33.events.toClient.MVEvent;
import it.polimi.ingsw.PSP33.events.toClient.data.DataBoard;
import it.polimi.ingsw.PSP33.events.toClient.data.DataCell;
import it.polimi.ingsw.PSP33.events.toClient.data.DataPawn;
import it.polimi.ingsw.PSP33.events.toClient.data.DataPlayer;
import it.polimi.ingsw.PSP33.events.toClient.setup.CurrentPlayer;
import it.polimi.ingsw.PSP33.events.toClient.setup.PossiblePlacement;
import it.polimi.ingsw.PSP33.events.toClient.setup.SelectGods;
import it.polimi.ingsw.PSP33.events.toClient.setup.YourGod;
import it.polimi.ingsw.PSP33.events.toClient.turn.*;

import it.polimi.ingsw.PSP33.events.toServer.VCEvent;
import it.polimi.ingsw.PSP33.events.toServer.turn.NewTurn;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Player;
import it.polimi.ingsw.PSP33.utils.patterns.observable.Listener;
import it.polimi.ingsw.PSP33.view.AbstractView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GameHandler extends AbstractView implements Runnable, Listener {

    /**
     * Lobby ID used for debug
     */
    private final int lobbyID;

    /**
     * Clients list
     */
    private final List<ClientHandler> clientHandlers;

    /**
     * Current turn client
     */
    private ClientHandler currentClient;

    /**
     * Boolean list to check if all clients are ready to play
     */
    private final List<Boolean> areClientsReady;

    /**
     * Constructor of the class
     * @param lobby lobby of the game
     */
    public GameHandler(Lobby lobby) {
        this.lobbyID = lobby.getLobbyID();
        this.clientHandlers = lobby.getClientHandlers();
        this.areClientsReady = lobby.getAreClientsReady();
    }

    @Override
    public void run() {
        setMVC();
        waitClientsSetup();
        /* Start game set up */
    }

    public void setMVC() {
        //List of players from clients' data
        List<Player> players = new ArrayList<>();
        for(ClientHandler clientHandler : clientHandlers) {
            players.add(new Player(clientHandler.getClientName(), clientHandler.getClientColor()));
        }

        //New model and controller
        Model model = new Model(players);
        Controller controller = new Controller(model);

        //Observer pattern
        model.addObserver(this);
        this.addObserver(controller);

        System.out.println("DEBUG_" + lobbyID +": set mvc over.");
    }

    public void waitClientsSetup() {
        synchronized (areClientsReady) {
            try {
                areClientsReady.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("DEBUG_" + lobbyID + ": wait for clients setup over.");
    }

    public synchronized void didReceiveMessage(VCEvent vcEvent) {
        notifyObservers(vcEvent);
    }

    public synchronized void sendMessageToClient(MVEvent mvEvent) {
        try {
            currentClient.sendMessage(mvEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void sendMessageToAll(MVEvent mvEvent) {
        for(ClientHandler clientHandler : clientHandlers) {
            try {
                clientHandler.sendMessage(mvEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public ClientHandler getClientHandlerByName(String name) {
        for(ClientHandler clientHandler : clientHandlers) {
            if(clientHandler.getClientName().equals(name)) {
                return clientHandler;
            }
        }

        return null;
    }

    public void setCurrentClient(ClientHandler currentClient) {
        this.currentClient = currentClient;
    }

    @Override
    public void update(MVEvent message) {
        message.accept(this);
    }

    @Override
    public void visit(DataBoard dataBoard) {
        sendMessageToAll(dataBoard);
    }

    @Override
    public void visit(DataCell dataCell) {
        sendMessageToAll(dataCell);
    }

    @Override
    public void visit(DataPlayer dataPlayer) {
        sendMessageToAll(dataPlayer);
    }

    @Override
    public void visit(DataPawn dataPawn) {
        sendMessageToAll(dataPawn);
    }

    @Override
    public void visit(YourGod yourGod) {

    }

    @Override
    public void visit(CurrentPlayer currentPlayer) {
        setCurrentClient(getClientHandlerByName(currentPlayer.getName()));
    }

    @Override
    public void visit(PossiblePlacement possiblePlacement) {
        sendMessageToClient(possiblePlacement);
    }

    @Override
    public void visit(SelectGods selectGods) {

    }

    @Override
    public void visit(YouLose youLose) {
        sendMessageToClient(youLose);
    }

    @Override
    public void visit(YouWin youWin) {
        sendMessageToClient(youWin);
    }

    @Override
    public void visit(NewAction newAction) {
        sendMessageToClient(newAction);
    }

    @Override
    public void visit(PossibleBuild possibleBuild) {
        sendMessageToClient(possibleBuild);
    }

    @Override
    public void visit(PossibleMove possibleMove) {
        sendMessageToClient(possibleMove);
    }

    @Override
    public void visit(PossibleExtraAction possibleExtraAction) {
        sendMessageToClient(possibleExtraAction);
    }
}
