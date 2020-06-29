package it.polimi.ingsw.PSP33.client;

import it.polimi.ingsw.PSP33.events.to_client.CCEventVisitor;
import it.polimi.ingsw.PSP33.events.to_client.connection.*;

import java.net.Socket;

public class ServerAdapterGUI extends ServerAdapter {

    /**
     * Constructor of the class
     *
     * @param server server's socket
     */
    public ServerAdapterGUI(Socket server) {
        super(server);
    }


    @Override
    public void visit(SelectConnection selectConnection) {

    }

    @Override
    public void visit(SelectNumberOfPlayers selectNumberOfPlayers) {

    }

    @Override
    public void visit(SelectLobby selectLobby) {

    }

    @Override
    public void visit(SelectName selectName) {

    }

    @Override
    public void visit(SelectColor selectColor) {

    }

    @Override
    public void visit(RequestWait requestWait) {

    }

    @Override
    public void visit(AllPlayersReady allPlayersReady) {

    }
}
