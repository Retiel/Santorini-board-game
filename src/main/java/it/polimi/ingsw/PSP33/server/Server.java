package it.polimi.ingsw.PSP33.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;


public class Server implements Runnable {

    /**
     * Server's socket
     */
    private static ServerSocket serverSocket = null;

    /**
     * Server's socket port
     */
    public final static int SOCKET_PORT = 11212;

    public static void main(String[] args) {
        Server server = new Server();

        try {
            //Open server's socket
            serverSocket = new ServerSocket(SOCKET_PORT);
        } catch (IOException e) {
            System.out.println("Cannot open server socket");
            System.exit(1);
        }

        server.run();
    }

    @Override
    public void run() {

        //Debug
        int i = 1;

        while (true) {

            Lobby lobby = new Lobby(i);

            try {
                //Accept new client connection
                Socket first = serverSocket.accept();
                ClientHandler clientHandler = new ClientHandler(first, lobby);

                //Get number of players from first connected client
                lobby.setNumberOfPlayers(clientHandler.requestNumberOfPlayers());

                //Update list of client handlers
                lobby.getClientHandlers().add(clientHandler);

                //Start new thread for client handler
                Thread thread = new Thread(clientHandler, "server_" + first.getInetAddress());
                thread.start();
            } catch (IOException | ClassNotFoundException e) {
                e.printStackTrace();
            }

            while (lobby.getClientHandlers().size() < lobby.getNumberOfPlayers()) {
                try {
                    //Accept new client connection
                    Socket client = serverSocket.accept();
                    ClientHandler clientHandler = new ClientHandler(client, lobby);

                    //Update list of client handlers
                    lobby.getClientHandlers().add(clientHandler);

                    //Start new thread for client handler
                    Thread thread = new Thread(clientHandler, "server_" + client.getInetAddress());
                    thread.start();

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            //Wait for all clients to make their selection
            while (!lobby.isSelectionOver()) {}

            lobby.startGame();

            i++;
        }
    }
}
