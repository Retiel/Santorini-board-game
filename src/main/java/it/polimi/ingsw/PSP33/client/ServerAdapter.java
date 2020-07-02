package it.polimi.ingsw.PSP33.client;

import it.polimi.ingsw.PSP33.events.EventSerializer;
import it.polimi.ingsw.PSP33.events.to_client.CCEvent;
import it.polimi.ingsw.PSP33.events.to_client.CCEventVisitor;
import it.polimi.ingsw.PSP33.events.to_client.MVEvent;
import it.polimi.ingsw.PSP33.events.to_client.turn.YouLose;
import it.polimi.ingsw.PSP33.events.to_client.turn.YouWin;
import it.polimi.ingsw.PSP33.events.to_server.SCEvent;
import it.polimi.ingsw.PSP33.events.to_server.VCEvent;
import it.polimi.ingsw.PSP33.utils.observable.Observable;
import it.polimi.ingsw.PSP33.utils.observable.Observer;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Abstract class that handles all client's communication with the server
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
     * Boolean to check if setup is over
     */
    private boolean isSetupOver;

    /**
     * Event serializer
     */
    private final EventSerializer eventSerializer;

    /**
     * ExecutorService used to run tasks on a dedicated thread
     */
    private final ExecutorService executor;

    /**
     * Constructor of the class
     * @param server server's socket
     */
    public ServerAdapter(Socket server) {
        this.server = server;
        this.input = null;
        this.output = null;
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
     * Method that sends a view-controller events to the server
     * @param vcEvent view-controller event
     */
    public void sendVCEvent(VCEvent vcEvent) {
        String vcJson = eventSerializer.serializeVC(vcEvent);
        try {
            output.writeUTF(vcJson);
        } catch (IOException e) {
            System.out.println("Disconnected from game - Unable to send event");
        }
    }

    /**
     * Method that receives model-view events from the server
     */
    public void receiveMVEvent() {
        while (true) {
            String mvJson;

            try {
                mvJson = input.readUTF();
            } catch (IOException e) {
                System.out.println("Disconnected from game - Unable to receive event");
                //System.exit(0);
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
     * Method that sends a server-connection events to the server
     * @param scEvent server-connection event
     */
    public void sendSCEvent(SCEvent scEvent) {
        String scJson = eventSerializer.serializeSC(scEvent);
        try {
            output.writeUTF(scJson);
        } catch (IOException e) {
            System.out.println("Disconnected from setup - Unable to send event");
        }
    }

    /**
     * Method that receives client-connection events from the server
     */
    public void receiveCCEvent() {
        while (true) {
            String ccJson;

            try {
                ccJson = input.readUTF();
            } catch (IOException e) {
                System.out.println("Disconnected from setup - Unable to receive event");
                //System.exit(0);
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

    /**
     * Method to get the socket's input stream
     * @return socket's input stream
     */
    public DataInputStream getInput() {
        return input;
    }

    /**
     * Method to get the socket's output stream
     * @return socket's output stream
     */
    public DataOutputStream getOutput() {
        return output;
    }

    /**
     * Method to set setupOver
     * @param setupOver true if setup is over
     */
    public void setSetupOver(boolean setupOver) {
        isSetupOver = setupOver;
    }
}
