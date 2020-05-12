package it.polimi.ingsw.PSP33.client;

import it.polimi.ingsw.PSP33.server.Server;
import it.polimi.ingsw.PSP33.view.AbstractView;
import it.polimi.ingsw.PSP33.view.ViewFactory;

import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;


public class Client implements Runnable {

    private AbstractView view;

    public static void main(String[] args) {
        Client client = new Client();
        client.run();
    }

    @Override
    public void run() {

        getViewSelection();

        Socket server;
        try {
            server = new Socket("127.0.0.1", Server.SOCKET_PORT);
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

        System.out.println("Game starts!");

        //Game started and View has to start
    }

    public void getViewSelection() {

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
    }
}
