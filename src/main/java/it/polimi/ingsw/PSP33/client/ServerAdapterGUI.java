package it.polimi.ingsw.PSP33.client;

import it.polimi.ingsw.PSP33.events.to_client.connection.*;
import it.polimi.ingsw.PSP33.events.to_server.connection.*;
import it.polimi.ingsw.PSP33.view.gui.SetupFrame;

import javax.swing.*;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Server adapter for GUI
 */
public class ServerAdapterGUI extends ServerAdapter {

    /**
     * Frame used for the setup
     */
    private SetupFrame setupFrame;

    /**
     * Constructor of the class
     *
     * @param server server's socket
     */
    public ServerAdapterGUI(Socket server) {
        super(server);

        SwingUtilities.invokeLater(() -> setupFrame = new SetupFrame());
    }


    @Override
    public void visit(SelectConnection selectConnection) {
        SwingUtilities.invokeLater(() -> {
            int selection = setupFrame.selectConnection();

            sendSCEvent(new ConnectionSelected(selection));
        });
    }

    @Override
    public void visit(SelectNumberOfPlayers selectNumberOfPlayers) {
        SwingUtilities.invokeLater(() -> {
            int selection = setupFrame.selectNumberOfPlayers();

            sendSCEvent(new NumberOfPlayersSelected(selection));
        });
    }

    @Override
    public void visit(SelectLobby selectLobby) {
        SwingUtilities.invokeLater(() -> {
            List<String> lobbies = new ArrayList<>(selectLobby.getLobbies().values());

            int selection = setupFrame.selectLobby(lobbies);

            Set<Integer> keySet = selectLobby.getLobbies().keySet();
            List<Integer> keyList = new ArrayList<>(keySet);

            sendSCEvent(new LobbySelected(keyList.get(selection)));
        });
    }

    @Override
    public void visit(SelectName selectName) {
        SwingUtilities.invokeLater(() -> {
            String name = setupFrame.selectName();

            sendSCEvent(new NameSelected(name));
        });
    }

    @Override
    public void visit(SelectColor selectColor) {
        SwingUtilities.invokeLater(() -> {
            int selection = setupFrame.selectColor(selectColor.getColors());

            sendSCEvent(new ColorSelected(selectColor.getColors().get(selection)));
        });
    }

    @Override
    public void visit(RequestWait requestWait) {
        SwingUtilities.invokeLater(() -> setupFrame.requestWait());
    }

    @Override
    public void visit(AllPlayersReady allPlayersReady) {
        SwingUtilities.invokeLater(() -> setupFrame.dispose());

        setSetupOver(true);

        handleServerConnection();
    }
}
