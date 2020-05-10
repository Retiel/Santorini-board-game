package it.polimi.ingsw.PSP33.server;

import it.polimi.ingsw.PSP33.events.EventSerializer;
import it.polimi.ingsw.PSP33.events.toClient.MVEvent;
import it.polimi.ingsw.PSP33.events.toServer.VCEvent;
import it.polimi.ingsw.PSP33.utils.enums.Color;
import it.polimi.ingsw.PSP33.utils.patterns.observable.Listened;

import java.io.*;
import java.net.Socket;


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
    private final Lobby lobby;

    private final EventSerializer eventSerializer;

    /**
     * Constructor of the class
     * @param client client's socket
     * @param lobby client's lobby
     */
    public ClientHandler(Socket client, Lobby lobby){
        this.client = client;
        this.input = null;
        this.output = null;
        this.clientName = "";
        this.clientColor = null;
        this.lobby = lobby;
        this.eventSerializer = new EventSerializer();
    }

    @Override
    public void run() {
        try {
            handleClientSetup();
        } catch (IOException e) {
            e.printStackTrace();
        }

        //New thread to keep listening from socket
        new Thread(() -> {
            try {
                receiveMessage();
            } catch (IOException e) {
                lobby.getClientHandlers().remove(this);
                e.printStackTrace();
            }
        }, "ClientHandler_" + clientName + "_receiveMessage()").start();
    }

    /**
     * Method to handle the client connection. It waits for a new message from the input's stream and
     * notifies the game handler
     * @throws IOException unable to access the socket's stream
     */
    public void receiveMessage() throws IOException {
        while (true) {
            String json = input.readUTF();
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

        if(output == null && input == null) {
            output = new DataOutputStream(client.getOutputStream());
            input = new DataInputStream(client.getInputStream());
        }

        requestPlayerName();
        requestPlayerColor();
        requestWait();
    }

    /**
     * Request the client to type their name
     * @throws IOException unable to access the socket's stream
     */
    public void requestPlayerName() throws IOException {

        while (clientName.equals("")) {

            //Send request
            String string = "Type your name: ";
            output.writeUTF(string);

            //Receive selection
            string = input.readUTF();

            //Check name's uniqueness
            if(!lobby.checkName(string)) {
                lobby.addName(string);
                clientName = string;
            }
        }
    }

    /**
     * Request the client to select their color
     * @throws IOException unable to access the socket's stream
     */
    public void requestPlayerColor() throws IOException {

        while (clientColor == null) {

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
        String str = "Waiting for players..";
        output.writeUTF(str);

        while (true) {
            str = input.readUTF();
            if (str.equals("OK")) {
                lobby.setClientReady(this);
                break;
            }
        }
    }

    /**
     * Request the client to select the number of players for the game
     *
     * @return number of players
     * @throws IOException unable to access the socket's stream
     */
    public int requestNumberOfPlayers() throws IOException {

        output = new DataOutputStream(client.getOutputStream());
        input = new DataInputStream(client.getInputStream());

        int numberOfPlayers = 0;

        while(numberOfPlayers == 0) {

            //Send request
            String string = "Select number of players: \n1. 2 players \n2. 3 players";
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

        return numberOfPlayers;
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

    public Lobby getLobby() {
        return lobby;
    }
}
