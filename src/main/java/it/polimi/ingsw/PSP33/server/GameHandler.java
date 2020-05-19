package it.polimi.ingsw.PSP33.server;


import it.polimi.ingsw.PSP33.controller.Controller;
import it.polimi.ingsw.PSP33.events.toClient.MVEvent;
import it.polimi.ingsw.PSP33.events.toClient.data.DataBoard;
import it.polimi.ingsw.PSP33.events.toClient.data.DataCell;
import it.polimi.ingsw.PSP33.events.toClient.data.DataPlayer;
import it.polimi.ingsw.PSP33.events.toClient.setup.CurrentPlayer;
import it.polimi.ingsw.PSP33.events.toClient.setup.PossiblePlacement;
import it.polimi.ingsw.PSP33.events.toClient.setup.SelectGods;
import it.polimi.ingsw.PSP33.events.toClient.setup.YourGod;
import it.polimi.ingsw.PSP33.events.toClient.turn.*;
import it.polimi.ingsw.PSP33.events.toServer.VCEvent;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Player;
import it.polimi.ingsw.PSP33.utils.observable.Listener;
import it.polimi.ingsw.PSP33.view.AbstractView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Class that handles the game for one lobby
 */
public class GameHandler extends AbstractView implements Listener {

    /**
     * Lobby ID
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
     * Constructor of the class
     * @param lobby lobby of the game
     */
    public GameHandler(Lobby lobby) {
        this.lobbyID = lobby.getLobbyID();
        this.clientHandlers = lobby.getClientHandlers();
        this.currentClient = null;
    }

    /**
     * Method that starts the game
     */
    public void startGame() {
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

        System.out.println("Lobby_" + lobbyID +": observer done");

        //Controller starts the game
        controller.startGame();

        System.out.println("Lobby_" + lobbyID +": game starts");
    }

    @Override
    public synchronized void didReceiveMessage(VCEvent vcEvent) {
        notifyObservers(vcEvent);
    }

    /**
     * Method that sends a model-view event to the current client
     * @param mvEvent model-view event
     */
    public synchronized void sendMessageToClient(MVEvent mvEvent) {
        try {
            currentClient.sendMessage(mvEvent);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that sends a model-view event to all clients
     * @param mvEvent model-view event
     */
    public void sendMessageToAll(MVEvent mvEvent) {
        for(ClientHandler clientHandler : clientHandlers) {
            try {
                clientHandler.sendMessage(mvEvent);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method to get a client handler by its name
     * @param name client's name
     *
     * @return client handler with the name in input
     */
    public ClientHandler getClientHandlerByName(String name) {
        for(ClientHandler clientHandler : clientHandlers) {
            if(clientHandler.getClientName().equals(name)) {
                return clientHandler;
            }
        }

        return null;
    }

    /**
     * Method that sets the current client
     *
     * @param currentClient client handler to be set to current
     */
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
        for(ClientHandler clientHandler : clientHandlers) {
            dataPlayer.setName(clientHandler.getClientName());
            try {
                clientHandler.sendMessage(dataPlayer);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void visit(DataPawn dataPawn) {
        sendMessageToAll(dataPawn);
    }

    @Override
    public void visit(YourGod yourGod) {
        sendMessageToClient(yourGod);
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
        sendMessageToClient(selectGods);
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
