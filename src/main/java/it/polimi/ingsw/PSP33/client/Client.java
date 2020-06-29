package it.polimi.ingsw.PSP33.client;

import com.google.gson.Gson;
import it.polimi.ingsw.PSP33.utils.Connection;
import it.polimi.ingsw.PSP33.view.AbstractView;
import it.polimi.ingsw.PSP33.view.ViewFactory;

import java.io.*;
import java.net.Socket;

/**
 * Class that handles client's connection and client's view
 */
public class Client {

    public static void main(String[] args) {

        int viewSelection;

        //TODO: args.length > 0 with parameter --cli from jar
        if(args.length == 0) {
            viewSelection = 1;
        } else {
            viewSelection = 2;
        }

        AbstractView view = ViewFactory.getView(viewSelection);

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

        ServerAdapter serverAdapter;
        if(viewSelection == 1) {
            serverAdapter = new ServerAdapterCLI(server);
        } else {
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

    private static Reader getConnectionReader() {
        InputStream input = Client.class.getResourceAsStream("/connection.json");

        return new BufferedReader(new InputStreamReader(input));
    }
}
