package it.polimi.ingsw.PSP33.events.to_server.connection;

import it.polimi.ingsw.PSP33.events.to_server.SCEvent;
import it.polimi.ingsw.PSP33.events.to_server.SCEventVisitor;

public class LobbySelected implements SCEvent {

    private final int lobbyID;

    public LobbySelected(int lobbyID) {
        this.lobbyID = lobbyID;
    }

    public int getLobbyID() {
        return lobbyID;
    }

    @Override
    public void accept(SCEventVisitor scEventVisitor) {
        scEventVisitor.visit(this);
    }
}
