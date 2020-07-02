package it.polimi.ingsw.PSP33.server;


import it.polimi.ingsw.PSP33.controller.Controller;
import it.polimi.ingsw.PSP33.events.to_client.MVEvent;
import it.polimi.ingsw.PSP33.events.to_client.data.DataBoard;
import it.polimi.ingsw.PSP33.events.to_client.data.DataCell;
import it.polimi.ingsw.PSP33.events.to_client.data.DataPlayer;
import it.polimi.ingsw.PSP33.events.to_client.setup.CurrentPlayer;
import it.polimi.ingsw.PSP33.events.to_client.setup.PossiblePlacement;
import it.polimi.ingsw.PSP33.events.to_client.setup.SelectGods;
import it.polimi.ingsw.PSP33.events.to_client.setup.YourGod;
import it.polimi.ingsw.PSP33.events.to_client.turn.*;
import it.polimi.ingsw.PSP33.events.to_server.VCEvent;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Player;
import it.polimi.ingsw.PSP33.utils.CustomDebugger;
import it.polimi.ingsw.PSP33.utils.observable.Listener;
import it.polimi.ingsw.PSP33.view.AbstractView;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that handles the game for one lobby
 */
public class GameHandler extends AbstractView implements Listener {


    /**
     * Boolean used to stop sending messages
     */
    private boolean toggle;

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
        this.toggle = true;
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

        /* Testing item*/
        CustomDebugger customDebugger = new CustomDebugger(lobbyID, model, controller);
        Thread thread = new Thread(customDebugger);
        System.out.println("Lobby_" + lobbyID +": debugger active");
        thread.start();
        /* end testing items */

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
        if(toggle) currentClient.sendMVEvent(mvEvent);
    }

    /**
     * Method that sends a model-view event to all clients
     * @param mvEvent model-view event
     */
    public void sendMessageToAll(MVEvent mvEvent) {
        for(ClientHandler clientHandler : clientHandlers) {
            if(toggle) clientHandler.sendMVEvent(mvEvent);
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
            clientHandler.sendMVEvent(dataPlayer);
        }
    }

    @Override
    public void visit(YourGod yourGod) {
        sendMessageToClient(yourGod);
    }

    @Override
    public void visit(CurrentPlayer currentPlayer) {
        setCurrentClient(getClientHandlerByName(currentPlayer.getName()));
        sendMessageToAll(currentPlayer);
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
        clientHandlers.remove(currentClient);
    }

    @Override
    public void visit(YouWin youWin) {

        String winner = youWin.getName();
        ClientHandler clientHandler = getClientHandlerByName(winner);
        setCurrentClient(clientHandler);
        sendMessageToClient(youWin);
        clientHandlers.remove(currentClient);
        sendMessageToAll(new YouLose(winner));

        toggle = false;
    }

    @Override
    public void visit(SelectPawn selectPawn) {
        sendMessageToClient(selectPawn);
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
