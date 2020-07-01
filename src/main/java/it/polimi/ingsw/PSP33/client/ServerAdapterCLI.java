package it.polimi.ingsw.PSP33.client;

import it.polimi.ingsw.PSP33.events.to_client.connection.*;
import it.polimi.ingsw.PSP33.events.to_server.connection.*;

import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

/**
 * Server adapter for CLI
 */
public class ServerAdapterCLI extends ServerAdapter {

    /**
     * Scanner used to get the client's input
     */
    private Scanner scanner;

    /**
     * Constructor of the class
     *
     * @param server server's socket
     */
    public ServerAdapterCLI(Socket server) {
        super(server);
        scanner = new Scanner(System.in);
    }


    @Override
    public void visit(SelectConnection selectConnection) {
        System.out.println("Select how you want to connect:\n1. Create lobby\n2. Join lobby");
        int selection = readInput(2);

        sendSCEvent(new ConnectionSelected(selection));
    }

    @Override
    public void visit(SelectNumberOfPlayers selectNumberOfPlayers) {
        System.out.println("Select number of players for the lobby:\n1. 2 players\n2. 3 players");
        int selection = readInput(2);

        sendSCEvent(new NumberOfPlayersSelected(selection));
    }

    @Override
    public void visit(SelectLobby selectLobby) {
        System.out.println("Select lobby:");
        printList(selectLobby.getLobbies().values());

        int selection = readInput(selectLobby.getLobbies().values().size());

        Set<Integer> keySet = selectLobby.getLobbies().keySet();
        List<Integer> keyList = new ArrayList<>(keySet);

        sendSCEvent(new LobbySelected(keyList.get(selection - 1)));
    }

    @Override
    public void visit(SelectName selectName) {
        System.out.println("Type your name:");
        String name = scanner.next();

        sendSCEvent(new NameSelected(name));
    }

    @Override
    public void visit(SelectColor selectColor) {
        System.out.println("Select color:");
        printList(selectColor.getColors());

        int selection = readInput(selectColor.getColors().size());

        sendSCEvent(new ColorSelected(selectColor.getColors().get(selection - 1)));
    }

    @Override
    public void visit(RequestWait requestWait) {
        System.out.println("Waiting for players..");
    }

    @Override
    public void visit(AllPlayersReady allPlayersReady) {
        System.out.println("All players are ready");

        setSetupOver(true);

        handleServerConnection();
    }

    /**
     * Method that reads integer input from the client
     * @param size range of accepted integer inputs
     * @return integer input
     */
    private int readInput(int size){
        if(scanner.hasNextInt()){
            int i = scanner.nextInt();
            if(i <= size && i > 0) {
                return i;
            } else {
                System.out.println("Invalid Choice (integer out of bound), please select again:");
                return readInput(size);}
        }
        else {
            System.out.println("Invalid Choice (mismatch input type), please select again:");
            scanner.next();
            return readInput(size);
        }
    }

    /**
     * Method used to print a list of generic elements
     * @param list list to print
     * @param <T> iterable generic
     * @param <E> type of elements of the list
     */
    public <T extends Iterable<E>, E> void printList(T list){
        StringBuilder stringBuilder = new StringBuilder();

        int counter = 1;
        for (E choice : list) {
            stringBuilder
                    .append(counter)
                    .append(". ")
                    .append(choice.toString())
                    .append("\n");

            counter++;
        }

        stringBuilder.deleteCharAt(stringBuilder.length() - 1);

        System.out.println(stringBuilder.toString());
    }
}
