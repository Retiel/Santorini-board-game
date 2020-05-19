package it.polimi.ingsw.PSP33.server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server class that handles the connection requests from clients
 */
public class Server implements Runnable {

    /**
     * Server's socket
     */
    private static ServerSocket serverSocket = null;

    /**
     * Server's socket port
     */
    public final static int SOCKET_PORT = 7777;

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

        while(true) {
            try {
                Socket client = serverSocket.accept();

                ClientHandler clientHandler = new ClientHandler(client);

                Thread thread = new Thread(clientHandler, "Server" + client.getInetAddress());
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
