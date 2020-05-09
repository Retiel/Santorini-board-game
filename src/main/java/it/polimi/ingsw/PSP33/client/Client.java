package it.polimi.ingsw.PSP33.client;

import it.polimi.ingsw.PSP33.server.Server;
import it.polimi.ingsw.PSP33.utils.patterns.observable.Listener;
import it.polimi.ingsw.PSP33.view.ViewFactory;
import it.polimi.ingsw.PSP33.view.View;

import java.io.IOException;
import java.net.Socket;


public class Client implements Runnable {

    private View view;
    private ServerAdapter serverAdapter;

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

    @Override
    public void run() {

        Socket server;
        try {
            server = new Socket("127.0.0.1", Server.SOCKET_PORT);
        } catch (IOException e) {
            System.out.println("Server unreachable");
            return;
        }
        System.out.println("Connected");

        serverAdapter = new ServerAdapter(server);

        Thread thread = new Thread(serverAdapter);
        thread.start();

        synchronized (serverAdapter) {
            try {
                serverAdapter.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        view = ViewFactory.getView(serverAdapter.getViewSelection());

        serverAdapter.addObserver(view);
        view.addObserver(serverAdapter);

        try {
            serverAdapter.selectionOver();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
