package it.polimi.ingsw.PSP33.client;

import com.google.gson.Gson;
import it.polimi.ingsw.PSP33.utils.Connection;
import it.polimi.ingsw.PSP33.view.AbstractView;
import it.polimi.ingsw.PSP33.view.cli.CLI;
import it.polimi.ingsw.PSP33.view.gui.GUI;

import java.io.*;
import java.net.Socket;

/**
 * Class that handles client's connection and client's view
 */
public class Client {

    public static void main(String[] args) {

        AbstractView view;
        ServerAdapter serverAdapter;

        Gson gson = new Gson();
        Connection connection = gson.fromJson(getConnectionReader(), Connection.class);

        Socket server;
        try {
            server = new Socket(connection.getServer(), connection.getPort());
        } catch (IOException e) {
            System.out.println("Server unreachable");
            return;
        }
        System.out.println("Connected");

        if(args.length > 0) {
            //CLI
            view = new CLI();
            serverAdapter = new ServerAdapterCLI(server);
        } else {
            //GUI
            view = new GUI();
            serverAdapter = new ServerAdapterGUI(server);
        }

        serverAdapter.addObserver(view);
        view.addObserver(serverAdapter);

        Thread thread = new Thread(serverAdapter);
        thread.start();

        synchronized (serverAdapter) {
            try {
                serverAdapter.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method used to to fetch the connection json used to connect the client to the server
     * @return reader used by Gson
     */
    private static Reader getConnectionReader() {
        InputStream input = Client.class.getResourceAsStream("/connection.json");

        return new BufferedReader(new InputStreamReader(input));
    }
}
