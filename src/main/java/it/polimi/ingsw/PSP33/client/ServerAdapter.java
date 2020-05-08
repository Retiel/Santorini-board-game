package it.polimi.ingsw.PSP33.client;

import it.polimi.ingsw.PSP33.utils.patterns.observable.Listened;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ServerAdapter extends Listened implements Runnable {

    /**
     * Server's socket
     */
    private Socket server;

    /**
     * Socket's input stream
     */
    private DataInputStream input;

    /**
     * Socket's output stream
     */
    private DataOutputStream output;

    private Scanner scanner;

    public ServerAdapter(Socket server) {
        this.server = server;
        this.input = null;
        this.output = null;
        scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        try {
            input = new DataInputStream(server.getInputStream());
            output = new DataOutputStream(server.getOutputStream());

            handleServerSetup();
        } catch (IOException e) {
            System.out.println("Disconnected");
            e.printStackTrace();
        }
    }

    public void handleServerSetup() throws IOException {

        while (true) {
            String str = input.readUTF();

            notifyListener(str);
        }
    }

    public void sendMessage(String json) throws IOException {
        output.writeUTF(json);
    }
}
