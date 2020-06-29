package it.polimi.ingsw.PSP33.client;

import it.polimi.ingsw.PSP33.events.EventSerializer;
import it.polimi.ingsw.PSP33.events.to_client.MVEvent;
import it.polimi.ingsw.PSP33.events.to_client.CCEvent;
import it.polimi.ingsw.PSP33.events.to_client.CCEventVisitor;
import it.polimi.ingsw.PSP33.events.to_client.turn.YouLose;
import it.polimi.ingsw.PSP33.events.to_client.turn.YouWin;
import it.polimi.ingsw.PSP33.events.to_server.VCEvent;
import it.polimi.ingsw.PSP33.events.to_server.SCEvent;
import it.polimi.ingsw.PSP33.utils.observable.Observable;
import it.polimi.ingsw.PSP33.utils.observable.Observer;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Class that handles all client's communication with the server
 */
public abstract class ServerAdapter extends Observable<MVEvent> implements Runnable, Observer<VCEvent>, CCEventVisitor {

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

    private final ExecutorService executor;

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
        this.executor = Executors.newSingleThreadExecutor();
    }

    @Override
    public void run() {
        try {
            //Opens streams from socket
            input = new DataInputStream(server.getInputStream());
            output = new DataOutputStream(server.getOutputStream());
        } catch (IOException e) {
            System.out.println("Disconnected");
        }

        handleServerSetup();
    }

    @Override
    public void update(VCEvent message) {
        sendVCEvent(message);
    }

    /**
     * Method that sends a view-controller event to the server
     * @param vcEvent view-controller event
     */
    public void sendVCEvent(VCEvent vcEvent) {
        String vcJson = eventSerializer.serializeVC(vcEvent);
        try {
            output.writeUTF(vcJson);
        } catch (IOException e) {
            System.out.println("Disconnected from game");
        }
    }

    /**
     * Method that receives model-view events from server
     */
    public void receiveMVEvent() {
        while (true) {
            String mvJson;

            try {
                mvJson = input.readUTF();
            } catch (IOException e) {
                System.out.println("Disconnected from game");
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

    public void sendSCEvent(SCEvent scEvent) {
        String scJson = eventSerializer.serializeSC(scEvent);
        try {
            output.writeUTF(scJson);
        } catch (IOException e) {
            System.out.println("Disconnected from setup");
        }
    }

    public void receiveCCEvent() {
        while (true) {
            String ccJson;

            try {
                ccJson = input.readUTF();
            } catch (IOException e) {
                System.out.println("Disconnected from setup");
                break;
            }

            CCEvent ccEvent = eventSerializer.deserializeCC(ccJson);
            ccEvent.accept(this);

            if(isSetupOver) {
                break;
            }
        }
    }

    /**
     * Method that handles the client's server connection
     */
    public void handleServerConnection() {
        executor.execute(this::receiveMVEvent);
    }

    /**
     * Method that handles the client's server setup
     */
    public void handleServerSetup() {
        executor.execute(this::receiveCCEvent);
    }

    public DataInputStream getInput() {
        return input;
    }

    public DataOutputStream getOutput() {
        return output;
    }

    public Scanner getScanner() {
        return scanner;
    }

    public void setSetupOver(boolean setupOver) {
        isSetupOver = setupOver;
    }

    public Object getLock() {
        return lock;
    }
}
