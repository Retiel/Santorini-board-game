package it.polimi.ingsw.PSP33.client;

import it.polimi.ingsw.PSP33.events.EventSerializer;
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

    private final EventSerializer eventSerializer;

    public ServerAdapter(Socket server) {
        this.server = server;
        this.input = null;
        this.output = null;
        this.scanner = new Scanner(System.in);
        this.viewSelection = 0;
        this.eventSerializer = new EventSerializer();
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

        new Thread(() -> {
            try {
                receiveMessage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();
    }

    public void receiveMessage() throws IOException {
        while (true) {
            String mvJson = input.readUTF();
            MVEvent mvEvent = eventSerializer.deserializeMV(mvJson);

            notifyObservers(mvEvent);
        }
    }

    public void sendMessage(VCEvent vcEvent) throws IOException {
        String vcJson = eventSerializer.serializeVC(vcEvent);
        output.writeUTF(vcJson);
    }

    public void handleServerSetup() throws IOException {

        new Thread(() -> {
            try {
                getClientInput();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }).start();

        while (true) {
            String str = input.readUTF();

            if(str.equals("Waiting for players..")) {
                selectView();
            }

            System.out.println(str);
        }
    }

    public void getClientInput() throws IOException {
        while (true) {
            String str = scanner.nextLine();
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

    @Override
    public void update(VCEvent message) {
        try {
            sendMessage(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
