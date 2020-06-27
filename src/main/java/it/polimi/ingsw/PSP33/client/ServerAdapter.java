package it.polimi.ingsw.PSP33.client;

import it.polimi.ingsw.PSP33.events.EventSerializer;
import it.polimi.ingsw.PSP33.events.toClient.MVEvent;
import it.polimi.ingsw.PSP33.events.toClient.turn.YouLose;
import it.polimi.ingsw.PSP33.events.toClient.turn.YouWin;
import it.polimi.ingsw.PSP33.events.toServer.VCEvent;
import it.polimi.ingsw.PSP33.utils.observable.Observable;
import it.polimi.ingsw.PSP33.utils.observable.Observer;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

/**
 * Class that handles all client's communication with the server
 */
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
     * Boolean to check if setup is over
     */
    private boolean isSetupOver;

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
        this.eventSerializer = EventSerializer.getInstance();
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
        sendMessage(message);
    }

    /**
     * Method that sends a view-controller event to the server
     * @param vcEvent view-controller event
     */
    public void sendMessage(VCEvent vcEvent) {
        String vcJson = eventSerializer.serializeVC(vcEvent);
        try {
            output.writeUTF(vcJson);
        } catch (IOException e) {
            System.out.println("Other player disconnected");
        }
    }

    /**
     * Method that receives model-view events from server
     */
    public void receiveMessage() {
        while (true) {
            String mvJson;

            try {
                mvJson = input.readUTF();
            } catch (IOException e) {
                System.out.println("closed connection");
                break;
            }

            MVEvent mvEvent = eventSerializer.deserializeMV(mvJson);

            /* manage win and lose disconnection */
            if(mvEvent instanceof YouLose){
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(mvEvent instanceof YouWin){
                try {
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            notifyObservers(mvEvent);
        }
    }

    /**
     * Method that handles the client's server connection
     */
    public void handleServerConnection() {
        new Thread(this::receiveMessage).start();
    }

    /**
     * Method that handles the client's server setup
     */
    public void handleServerSetup() {

        //Starts listening to the server on a new thread
        new Thread(this::getServerSetup).start();

        while (true) {
            synchronized (lock) {
                try {
                    lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

            if (isSetupOver) {
                break;
            }

            String string = scanner.nextLine();
            try {
                output.writeUTF(string);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        synchronized (this) {
            try {
                this.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method that gets the setup requests from the server
     */
    public void getServerSetup() {
        loop: while (true) {
            String string;

            try {
                string = input.readUTF();
            } catch (IOException e) {
                e.printStackTrace();
                break;
            }

            System.out.println(string);

            switch (string) {
                case "Waiting for players...":
                    isSetupOver = true;

                    synchronized (lock) {
                        lock.notify();
                    }

                    break;

                case "All players are ready.":
                    synchronized (this) {
                        this.notifyAll();
                        break loop;
                    }

                default:
                    synchronized (lock) {
                        lock.notify();
                    }
            }
        }
    }
}
