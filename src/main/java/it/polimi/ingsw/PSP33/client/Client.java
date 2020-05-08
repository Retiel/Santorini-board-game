package it.polimi.ingsw.PSP33.client;

import it.polimi.ingsw.PSP33.server.Server;
import it.polimi.ingsw.PSP33.utils.patterns.observable.Listener;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;


public class Client implements Runnable, Listener {

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

        ServerAdapter serverAdapter = new ServerAdapter(server);
        serverAdapter.addListener(this);

        Thread thread = new Thread(serverAdapter);
        thread.start();

        while (true) {

            synchronized (this) {
                try {
                    wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            Scanner scanner = new Scanner(System.in);
            String str = scanner.nextLine();
            try {
                serverAdapter.sendMessage(str);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    @Override
    public void didReceiveMessage(String json) {
        System.out.println(json);

        synchronized (this) {
            notify();
        }
    }
}
