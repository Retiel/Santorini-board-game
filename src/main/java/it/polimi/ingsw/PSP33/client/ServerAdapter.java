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
     * Volatile boolean to check if setup is over
     */
    private volatile boolean isSetupOver;

    /**
     * Lock used to wait on server output
     */
    private final Object lock = new Object();

    /**
     * Event serializer
     */
    private final EventSerializer eventSerializer;

    /**
     * Constructor of the class
     * @param server server's socket
     */
    public ServerAdapter(Socket server) {
        this.server = server;
        this.input = null;
        this.output = null;
        this.scanner = new Scanner(System.in);
        this.isSetupOver = false;
        this.eventSerializer = new EventSerializer();
    }

    @Override
    public void run() {
        try {
            //Opens streams from socket
            input = new DataInputStream(server.getInputStream());
            output = new DataOutputStream(server.getOutputStream());
        } catch (IOException e) {
            System.out.println("Disconnected");
            e.printStackTrace();
        }

        handleServerSetup();
        handleServerConnection();
    }

    @Override
    public void update(VCEvent message) {
        System.out.println("update ok");
        sendMessage(message);
    }

    public void sendMessage(VCEvent vcEvent) {
        String vcJson = eventSerializer.serializeVC(vcEvent);
        try {
            System.out.println(vcJson);
            output.writeUTF(vcJson);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void receiveMessage() {
        while (true) {
            String mvJson;

            try {
                mvJson = input.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

            MVEvent mvEvent = eventSerializer.deserializeMV(mvJson);
            notifyObservers(mvEvent);
        }
    }

    public void handleServerConnection() {
        new Thread(this::receiveMessage).start();
    }

    public void handleServerSetup() {

        new Thread(this::getServerSetup).start();

        while (!isSetupOver) {

            synchronized (lock) {
                try {
                    lock.wait();
                    if (isSetupOver) {
                        try {
                            output.writeUTF("SETUP_OK");
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        break;
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            String string = scanner.nextLine();
            try {
                output.writeUTF(string);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void getServerSetup() {
        while (!isSetupOver) {
            String string;

            try {
                string = input.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

            System.out.println(string);

            if(string.equals("Waiting for players..")) {
                isSetupOver = true;
            }

            synchronized (lock) {
                lock.notify();
            }
        }

        while (true) {
            String string;

            try {
                string = input.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

            if(string.equals("GAME_OK")) {
                synchronized (this) {
                    this.notify();
                    break;
                }
            }
        }
    }
}
