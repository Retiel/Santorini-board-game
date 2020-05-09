package it.polimi.ingsw.PSP33.client;

import it.polimi.ingsw.PSP33.events.toClient.MVEvent;
import it.polimi.ingsw.PSP33.events.toServer.VCEvent;
import it.polimi.ingsw.PSP33.utils.patterns.observable.Observable;
import it.polimi.ingsw.PSP33.utils.patterns.observable.Observer;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class ServerAdapter extends Observable<MVEvent> implements Runnable, Observer<VCEvent> {

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

    /**
     * Scanner for client input
     */
    private Scanner scanner;

    /**
     * Client selection for his view
     */
    private int viewSelection;

    public ServerAdapter(Socket server) {
        this.server = server;
        this.input = null;
        this.output = null;
        scanner = new Scanner(System.in);

        viewSelection = 0;
    }

    @Override
    public void run() {
        try {
            input = new DataInputStream(server.getInputStream());
            output = new DataOutputStream(server.getOutputStream());

            handleServerSetup();
            handleServerConnection();
        } catch (IOException e) {
            System.out.println("Disconnected");
            e.printStackTrace();
        }
    }

    public void handleServerSetup() throws IOException {

        while (true) {
            String str = input.readUTF();

            if(str.equals("Waiting for players..")) {
                selectView();
            }

            System.out.println(str);

            str = scanner.nextLine();
            output.writeUTF(str);
        }
    }

    public void selectView() {
        while (viewSelection == 0) {
            System.out.println("Select user interface:\n1. CLI\n2. GUI");

            String string = scanner.nextLine();

            switch (string) {
                case "1":
                    viewSelection = 1;
                    break;

                case "2":
                    viewSelection = 2;
                    break;

                default:
                    continue;
            }

            synchronized (this) {
                notify();
            }
        }
    }

    public void selectionOver() throws IOException {
        output.writeUTF("OK");
    }

    public int getViewSelection() {
        return viewSelection;
    }

    public void handleServerConnection() throws IOException {
        while (true) {
            String json = input.readUTF();

            //Deserialization
            //Send MVEvent to View with notifyObservers()
        }
    }

    @Override
    public void update(VCEvent message) {
        //Send message to server
    }
}
