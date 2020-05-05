package it.polimi.ingsw.PSP33.server;

import it.polimi.ingsw.PSP33.utils.enums.Color;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;


public class ClientHandler implements Runnable {

    /**
     * Client's socket
     */
    private Socket client;

    /**
     * Socket's input stream
     */
    private ObjectInputStream input;

    /**
     * Socket's output stream
     */
    private ObjectOutputStream output;

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

    /**
     * List of clients' names
     */

    public ClientHandler(Socket client, Lobby lobby) {
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
            handleClientConnection();
        } catch (IOException | ClassNotFoundException e) {
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

    /**
     * Method to handle the client connection for the lobby setup
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void handleClientConnection() throws IOException, ClassNotFoundException {
        System.out.println("Connected to " + client.getInetAddress());

        if(output == null && input == null) {
            output = new ObjectOutputStream(client.getOutputStream());
            input = new ObjectInputStream(client.getInputStream());
        }

        requestPlayerName();
        requestPlayerColor();
        sendWaitMessage();
    }

    /**
     * Request the client to type their name
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public void requestPlayerName() throws IOException, ClassNotFoundException {

        while (clientName.equals("")) {
            String nameRequest = "Type your name: ";
            output.writeObject(nameRequest);

            Object next = input.readObject();
            String nameSelection = (String) next;

            if(!lobby.checkName(nameSelection)) {
                lobby.addName(nameSelection);
                clientName = nameSelection;
            }
        }
    }


    public void requestPlayerColor() throws IOException, ClassNotFoundException {

        while (clientColor == null) {
            List<Color> colorList = lobby.getColorList();
            String string = "Select your color: \n" + lobby.printColorList();
            output.writeObject(string);

            Object next = input.readObject();
            string = (String) next;

            int num;
            try {
                num = Integer.parseInt(string);
            } catch (NumberFormatException e) {
                num = 0;
            }

            if(num > 0 && num <= colorList.size()) {
            Color color = Color.getColorByIndex(num);
                if(lobby.checkColor(color)) {
                    lobby.removeColor(color);
                    clientColor = color;
                    break;
                }
            }
        }
    }




    /**
     * Tell the client to wait for other players
     * @throws IOException
     */
    public void sendWaitMessage() throws IOException {
        String str = "Waiting for players..";
        output.writeObject(str);
    }

    /**
     * Request the client to select the number of players for the game
     *
     * @return number of players
     * @throws IOException
     * @throws ClassNotFoundException
     */
    public int requestNumberOfPlayers() throws IOException, ClassNotFoundException {

        output = new ObjectOutputStream(client.getOutputStream());
        input = new ObjectInputStream(client.getInputStream());

        int numberOfPlayers = 0;

        while(numberOfPlayers == 0) {

            //Send request
            String string = "Select number of players: \n1. 2 players \n2. 3 players";
            output.writeObject(string);

            //Receive selection
            Object next = input.readObject();
            string = (String) next;
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
