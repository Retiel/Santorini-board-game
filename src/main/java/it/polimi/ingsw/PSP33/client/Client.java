package it.polimi.ingsw.PSP33.client;

import it.polimi.ingsw.PSP33.server.Server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;


public class Client implements Runnable {

    /**
     * Client's socket
     */
    Socket server;

    /**
     * Socket's input stream
     */
    ObjectInputStream input = null;

    /**
     * Socket's output stream
     */
    ObjectOutputStream output = null;

    /**
     * Scanner for client's input
     */
    Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

    @Override
    public void run() {
        try {
            server = new Socket("127.0.0.1", Server.SOCKET_PORT);
        } catch (IOException e) {
            System.out.println("Server unreachable");
            return;
        }
        System.out.println("Connected");

        try {
            input = new ObjectInputStream(server.getInputStream());
            output = new ObjectOutputStream(server.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }

        while (true) {
            try {
                String str = (String) input.readObject();
                System.out.println(str);

                str = scanner.nextLine();
                output.writeObject(str);

            } catch (IOException | ClassNotFoundException e) {
                System.out.println("Error");
                e.getStackTrace();
                break;
            }
        }
    }
}
