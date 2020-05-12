package it.polimi.ingsw.PSP33.server;

import it.polimi.ingsw.PSP33.events.EventSerializer;
import it.polimi.ingsw.PSP33.events.toClient.MVEvent;
import it.polimi.ingsw.PSP33.events.toServer.VCEvent;
import it.polimi.ingsw.PSP33.utils.enums.Color;
import it.polimi.ingsw.PSP33.utils.observable.Listened;

import java.io.*;
import java.net.Socket;


/**
 * Class that holds client's socket and all client's data
 */
public class ClientHandler extends Listened implements Runnable {

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

    /**
     * Client's lobby
     */
    private Lobby lobby;

    private final EventSerializer eventSerializer;

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
        this.lobby = null;
        this.eventSerializer = new EventSerializer();
    }

    @Override
    public void run() {
        try {
            handleConnectionSetup();
            handleClientSetup();
        } catch (IOException e) {
            if(lobby != null) {
                lobby.removeClient(this);
                return;
            }
            e.printStackTrace();
        }

        listenToClient();

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
     * Method that keeps listening to client's messages
     */
    public void listenToClient() {
        //New thread to keep listening from socket
        new Thread(this::receiveMessage, "ClientHandler_" + clientName + "_receiveMessage()").start();
    }

    /**
     * Method that keeps receiving client's events to notify game handler
     */
    public void receiveMessage() {
        while (true) {
            String json;

            try {
                json = input.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

            VCEvent vcEvent = eventSerializer.deserializeVC(json);
            notifyListener(vcEvent);
        }
    }

    /**
     * Method to send an event to the client
     * @param mvEvent model-view event
     * @throws IOException unable to access the socket's stream
     */
    public void sendMessage(MVEvent mvEvent) throws IOException {
        String mvJson = eventSerializer.serializeMV(mvEvent);
        output.writeUTF(mvJson);
    }

    /**
     * Method to handle the client connection for the lobby setup
     * @throws IOException unable to access the socket's stream
     */
    public void handleClientSetup() throws IOException {
        System.out.println("Connected to " + client.getInetAddress());

        requestPlayerName();
        requestPlayerColor();
        requestWait();
    }

    /**
     * Request the client to type his name
     * @throws IOException unable to access the socket's stream
     */
    public void requestPlayerName() throws IOException {
        while (true) {
            //Send request
            String string = "Type your name: ";
            output.writeUTF(string);

            //Receive selection
            string = input.readUTF();

            //Check name's uniqueness
            if(!lobby.checkName(string)) {
                lobby.addName(string);
                clientName = string;
                break;
            }
        }
    }

    /**
     * Request the client to select his color
     * @throws IOException unable to access the socket's stream
     */
    public void requestPlayerColor() throws IOException {
        while (true) {
            //Send request
            String string = "Select your color: \n" + lobby.printColorList();
            output.writeUTF(string);

            //Receive selection
            string = input.readUTF();

            Color color;
            switch (string) {
                case "1":
                    color = Color.getColorByIndex(1);
                    break;
                case "2":
                    color = Color.getColorByIndex(2);
                    break;
                case "3":
                    color = Color.getColorByIndex(3);
                    break;

                default:
                    continue;
            }

            if(lobby.checkColor(color)) {
                lobby.removeColor(color);
                clientColor = color;
                break;
            }
        }
    }

    /**
     * Tell the client to wait for other players
     * @throws IOException unable to access the socket's stream
     */
    public void requestWait() throws IOException {
        String string = "Waiting for players..";
        output.writeUTF(string);

        while (true) {
            string = input.readUTF();
            if (string.equals("SETUP_OK")) {
                lobby.setClientReady(this);
                break;
            }
        }
    }

    /**
     * Method that requests a client to be set ready
     */
    public void requestReady() {
        String string = "GAME_OK";

        try {
            output.writeUTF(string);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method that handles the client's connection setup
     * @throws IOException unable to access the socket's stream
     */
    public void handleConnectionSetup() throws IOException {
        output = new DataOutputStream(client.getOutputStream());
        input = new DataInputStream(client.getInputStream());

        boolean flag = true;
        while(flag) {
            if(LobbyManager.printLobbyList().equals("")) {
                output.writeUTF("No lobbies available. Creating a new lobby..");
                createLobby();
                flag = false;
            }else {
                String string = "Select how you want to connect:\n1. Create lobby\n2. Join lobby";
                output.writeUTF(string);

                string = input.readUTF();
                switch (string) {
                    case "1":
                        createLobby();
                        flag = false;
                        break;

                    case "2":
                        joinLobby();
                        flag = false;
                        break;

                    default:
                }
            }
        }
    }

    /**
     * Method that creates a new lobby and makes the client join it
     * @throws IOException unable to access the socket's stream
     */
    public void createLobby() throws IOException {

        int numberOfPlayers = 0;

        while(numberOfPlayers == 0) {

            //Send request
            String string = "Select number of players for the lobby:\n1. 2 players\n2. 3 players";
            output.writeUTF(string);

            //Receive selection
            string = input.readUTF();

            switch (string) {
                case "1":
                    numberOfPlayers = 2;
                    break;

                case "2":
                    numberOfPlayers = 3;
                    break;

                default:
            }
        }

        setLobby(LobbyManager.newLobby(numberOfPlayers));
    }

    /**
     * Method that makes the client join a lobby
     * @throws IOException unable to access the socket's stream
     */
    public synchronized void joinLobby() throws IOException {

        while (true) {
            String string = LobbyManager.printLobbyList();
            output.writeUTF(string);

            string = input.readUTF();

            int foo;
            try {
                foo = Integer.parseInt(string);
            }
            catch (NumberFormatException e)
            {
                foo = 0;
            }

            if(LobbyManager.checkLobbyList(foo)) {
                setLobby(LobbyManager.getLobbyByID(foo));
                break;
            }
        }
    }
}
