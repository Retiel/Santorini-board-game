package it.polimi.ingsw.PSP33.server;

import it.polimi.ingsw.PSP33.events.EventSerializer;
import it.polimi.ingsw.PSP33.events.to_client.CCEvent;
import it.polimi.ingsw.PSP33.events.to_client.MVEvent;
import it.polimi.ingsw.PSP33.events.to_client.connection.*;
import it.polimi.ingsw.PSP33.events.to_server.SCEvent;
import it.polimi.ingsw.PSP33.events.to_server.SCEventVisitor;
import it.polimi.ingsw.PSP33.events.to_server.VCEvent;
import it.polimi.ingsw.PSP33.events.to_server.connection.*;
import it.polimi.ingsw.PSP33.events.to_server.setup.PlayerDisconnected;
import it.polimi.ingsw.PSP33.utils.enums.Color;
import it.polimi.ingsw.PSP33.utils.observable.Listened;

import java.io.*;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * Class that holds client's socket and all client's data
 */
public class ClientHandler extends Listened implements Runnable, SCEventVisitor {

    /**
     * Client's socket
     */
    private final Socket client;

    /**
     * Socket's input stream
     */
    private DataInputStream input;

    /**
     * Socket's output stream
     */
    private DataOutputStream output;

    /**
     * Client's name
     */
    private String clientName;

    /**
     * Client's color
     */
    private Color clientColor;

    private String next;

    /**
     * Boolean used to check if setup is over
     */
    private boolean isSetupOver;

    /**
     * Client's lobby
     */
    private Lobby lobby;

    /**
     * Event serializer
     */
    private final EventSerializer eventSerializer;

    /**
     * ExecutorService to execute one task on a dedicated thread
     */
    private final ExecutorService executor;

    /**
     * Constructor of the class
     * @param client client's socket
     */
    public ClientHandler(Socket client){
        this.client = client;
        this.input = null;
        this.output = null;
        this.clientName = "";
        this.clientColor = null;
        this.isSetupOver = false;
        this.lobby = null;
        this.eventSerializer = EventSerializer.getInstance();
        this.executor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void run() {
        try {
            input = new DataInputStream(client.getInputStream());
            output = new DataOutputStream(client.getOutputStream());
        } catch (IOException e) {
            System.out.println("Lobby_0: " + client.getInetAddress() + " unable to open client's socket streams");
        }

        handleClientSetup();
    }

    /**
     * Method to get the client's name
     *
     * @return client's name
     */
    public String getClientName() {
        return clientName;
    }

    /**
     * Method to get the client's color
     *
     * @return client's color
     */
    public Color getClientColor() {
        return clientColor;
    }

    /**
     * Method to set setupOver
     * @param setupOver true if setup is over
     */
    public void setSetupOver(boolean setupOver) {
        isSetupOver = setupOver;
    }

    /**
     * Method to get the lobby
     *
     * @return lobby
     */
    public Lobby getLobby() {
        return lobby;
    }

    /**
     * Method to set the lobby
     * @param lobby lobby
     */
    public void setLobby(Lobby lobby) {
        this.lobby = lobby;
        lobby.addClient(this);
    }

    /**
     * Method that makes the method to receive game's events run on a dedicated thread
     */
    public void listenToGameEvents() {
        //New thread to keep listening from socket
        executor.execute(this::receiveVCEvent);
    }

    /**
     * Method that keeps receiving game's events to notify game handler
     */
    public void receiveVCEvent() {
        while (true) {
            String vcJson;

            try {
                vcJson = input.readUTF();
            } catch (IOException e) {
                System.out.println("Lobby_" + lobby.getLobbyID() + ": client_" + clientName + " disconnected");
                notifyListener(new PlayerDisconnected(getClientName()));
                break;
            }

            VCEvent vcEvent = eventSerializer.deserializeVC(vcJson);
            notifyListener(vcEvent);
        }
    }

    /**
     * Method to send a game event to the client
     * @param mvEvent model-view event
     */
    public void sendMVEvent(MVEvent mvEvent) {
        String mvJson = eventSerializer.serializeMV(mvEvent);
        try {
            output.writeUTF(mvJson);
        } catch (IOException e) {
            System.out.println("Lobby_" + lobby.getLobbyID() + ": " + getClientName() + " [mv_dropped]");
        }
    }

    /**
     * Method that makes the method to receive setup's events run on a dedicated thread
     */
    public void listenToSetupEvents() {
        //New thread to keep listening from socket
        executor.execute(this::receiveSCEvent);
    }

    /**
     * Method that keeps receiving setup's events until setup is over
     */
    public void receiveSCEvent() {
        while (true) {
            String scJson;

            try{
                scJson = input.readUTF();
            } catch (IOException e) {
                if(lobby != null) {
                    lobby.removeClient(this);
                    System.out.println("Lobby_" + lobby.getLobbyID() + ": "
                            + client.getInetAddress() + " disconnected and removed from lobby");
                } else {
                    System.out.println("Lobby_0: " + client.getInetAddress() + " disconnected before joining a lobby");
                }
                break;
            }

            SCEvent scEvent = eventSerializer.deserializeSC(scJson);
            scEvent.accept(this);

            if(isSetupOver) {
                listenToGameEvents();
                break;
            }
        }
    }

    /**
     * Method to send a setup event to the client
     * @param ccEvent client connection message
     */
    public void sendCCEvent(CCEvent ccEvent) {
        String ccJson = eventSerializer.serializeCC(ccEvent);
        try {
            output.writeUTF(ccJson);
        } catch (IOException e) {
            if(lobby != null) {
                //lobby.removeClient(this);
                System.out.println("Lobby_" + lobby.getLobbyID() + ": "
                        + client.getInetAddress() + " [cc_dropped]");
            } else {
                System.out.println("Lobby_0: " + client.getInetAddress() + " [cc_dropped]");
            }
        }
    }

    /**
     * Method to handle the client connection for the lobby setup
     */
    public void handleClientSetup() {
        System.out.println("Connected to " + client.getInetAddress());

        //Starts listening to setup events on a dedicated thread
        listenToSetupEvents();

        if(LobbyManager.getLobbies().values().size() == 0) {
            sendCCEvent(new SelectNumberOfPlayers());
        } else {
            sendCCEvent(new SelectConnection());
        }
    }

    /**
     * Method used by the Lobby to notify the client that setup is over
     */
    public void setReady() {

        sendCCEvent(new AllPlayersReady());

        //Starts listening to game events on a dedicated thread
        //listenToGameEvents();
    }

    @Override
    public void visit(ConnectionSelected connectionSelected) {
        switch (connectionSelected.getSelection()) {
            case 1:
                sendCCEvent(new SelectNumberOfPlayers());
                break;

            case 2:
                sendCCEvent(new SelectLobby(LobbyManager.getLobbyMap()));
                break;

            default:
                sendCCEvent(new SelectConnection());
        }
    }

    @Override
    public void visit(NumberOfPlayersSelected numberOfPlayersSelected) {
        switch (numberOfPlayersSelected.getSelection()) {
            case 1:
                setLobby(LobbyManager.newLobby(2));
                sendCCEvent(new SelectName());
                break;

            case 2:
                setLobby(LobbyManager.newLobby(3));
                sendCCEvent(new SelectName());
                break;

            default:
                sendCCEvent(new SelectNumberOfPlayers());
        }
    }

    @Override
    public void visit(LobbySelected lobbySelected) {
        //Check lobby selection
        if(LobbyManager.checkLobbyList(lobbySelected.getLobbyID())) {
            setLobby(LobbyManager.getLobbyByID(lobbySelected.getLobbyID()));
            sendCCEvent(new SelectName());
        } else {
            sendCCEvent(new SelectLobby(LobbyManager.getLobbyMap()));
        }
    }

    @Override
    public void visit(NameSelected nameSelected) {
        String name = nameSelected.getName();

        //Check name's uniqueness
        if(!name.equals("") && !lobby.checkName(name)) {
            lobby.addName(name);
            clientName = name;

            sendCCEvent(new SelectColor(lobby.getColorList()));
        } else {
            sendCCEvent(new SelectName());
        }
    }

    @Override
    public void visit(ColorSelected colorSelected) {
        Color color = colorSelected.getColor();

        //Check color uniqueness
        if(lobby.checkColor(color)) {
            lobby.removeColor(color);
            clientColor = color;

            setSetupOver(true);
            lobby.setClientReady(this);

            sendCCEvent(new RequestWait());
        } else {
            sendCCEvent(new SelectColor(lobby.getColorList()));
        }
    }
}
