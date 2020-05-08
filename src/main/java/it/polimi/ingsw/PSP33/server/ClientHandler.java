package it.polimi.ingsw.PSP33.server;

import it.polimi.ingsw.PSP33.utils.enums.Color;
import it.polimi.ingsw.PSP33.utils.patterns.observable.Listened;

import java.io.*;
import java.net.Socket;
import java.util.List;


public class ClientHandler extends Listened implements Runnable {

    /**
     * Client's socket
     */
    private Socket client;

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

    public ClientHandler(Socket client, Lobby lobby){
        this.client = client;
        this.input = null;
        this.output = null;
        this.clientName = "";
        this.clientColor = null;
        this.lobby = lobby;
    }

    @Override
    public void run() {
        try {
            handleClientSetup();
            handleClientConnection();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Getter for the name of the client
     *
     * @return client's name
     */
    public String getClientName() {
        return clientName;
    }

    public Color getClientColor() {
        return clientColor;
    }

    public void handleClientConnection() throws IOException {
        while (true) {
            String json = input.readUTF();
            notifyListener(json);
        }
    }

    /**
     * Method to handle the client connection for the lobby setup
     * @throws IOException
     */
    public void handleClientSetup() throws IOException {
        System.out.println("Connected to " + client.getInetAddress());

        if(output == null && input == null) {
            output = new DataOutputStream(client.getOutputStream());
            input = new DataInputStream(client.getInputStream());
        }

        requestPlayerName();
        requestPlayerColor();
        sendWaitMessage();
    }

    /**
     * Request the client to type their name
     * @throws IOException
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
     * @throws IOException
     */
    public void requestPlayerColor() throws IOException {

        while (clientColor == null) {

            //Get available colors from lobby
            List<Color> colorList = lobby.getColorList();

            //Send request
            String string = "Select your color: \n" + lobby.printColorList();
            output.writeUTF(string);

            //Receive selection
            string = input.readUTF();

            //Parse number
            int num;
            try {
                num = Integer.parseInt(string);
            } catch (NumberFormatException e) {
                num = 0;
            }

            //Check selected color
            if(num > 0 && num <= colorList.size()) {
            Color color = Color.getColorByIndex(num);
                if(lobby.checkColor(color)) {
                    lobby.removeColor(color);
                    clientColor = color;
                    break;
                }
            }
        }

        //TODO: change from setup to game on client side
    }

    /**
     * Tell the client to wait for other players
     * @throws IOException
     */
    public void sendWaitMessage() throws IOException {
        String str = "Waiting for players..";
        output.writeUTF(str);
    }

    /**
     * Request the client to select the number of players for the game
     *
     * @return number of players
     * @throws IOException
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

            //Parse number
            int num;
            try {
                num = Integer.parseInt(string);
            } catch (NumberFormatException e) {
                num = 0;
            }

            switch (num) {
                case 1:
                    numberOfPlayers = 2;
                    break;

                case 2:
                    numberOfPlayers = 3;
                    break;

                default:
            }
        }

        return numberOfPlayers;
    }
}
