package it.polimi.ingsw.PSP33.server;

import it.polimi.ingsw.PSP33.view.gui.SetupFrame;

import javax.swing.*;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Server class that handles the connection requests from clients
 */
public class Server {

    /**
     * Server's socket
     */
    private static ServerSocket serverSocket = null;

    /**
     * Server's socket port
     */
    public final static int SOCKET_PORT = 7777;

    public static void main(String[] args) {
        System.out.println("Server starting..");
        try {
            //Open server's socket
            serverSocket = new ServerSocket(SOCKET_PORT);
        } catch (IOException e) {
            System.out.println("Cannot open server socket");
            System.exit(1);
        }
        System.out.println("Server ready");

        SwingUtilities.invokeLater(Server::showServerWindow);

        while(true) {
            try {
                Socket client = serverSocket.accept();

                ClientHandler clientHandler = new ClientHandler(client);

                Thread thread = new Thread(clientHandler, "Server" + client.getInetAddress());
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }
        }
    }

    public static void showServerWindow() {
        JOptionPane optionPane = new JOptionPane("Server is running\nPress OK to close the server",
                JOptionPane.PLAIN_MESSAGE,
                JOptionPane.DEFAULT_OPTION);
        SetupFrame.lockClosure(null, optionPane);
        if((int) optionPane.getValue() == 0) {
            System.exit(0);
        }
    }
}
