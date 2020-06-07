package it.polimi.ingsw.PSP33.client;

import com.google.gson.Gson;
import it.polimi.ingsw.PSP33.server.Server;
import it.polimi.ingsw.PSP33.utils.Connection;
import it.polimi.ingsw.PSP33.view.AbstractView;
import it.polimi.ingsw.PSP33.view.ViewFactory;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 * Class that handles client's connection and client's view
 */
public class Client {

    public static void main(String[] args) {

        AbstractView view = ViewFactory.getView(1);

        Gson gson = new Gson();
        Connection connection = gson.fromJson("src/main/resources/connection.json", Connection.class);

        Socket server;
        try {
            server = new Socket(connection.getServer_ip(), connection.getPort());
        } catch (IOException e) {
            System.out.println("Server unreachable");
            return;
        }
        System.out.println("Connected");

        ServerAdapter serverAdapter = new ServerAdapter(server);

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
     * Method to make the clients select his view
     */
    public static AbstractView getViewSelection() {

        AbstractView view;

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Select user interface:\n1. CLI\n2. GUI");

            String string = scanner.nextLine();

            switch (string) {
                case "1":
                    view = ViewFactory.getView(1);
                    break;

                case "2":
                    view = ViewFactory.getView(2);
                    break;

                default:
                    continue;
            }
            break;
        }

        return view;
    }
}
