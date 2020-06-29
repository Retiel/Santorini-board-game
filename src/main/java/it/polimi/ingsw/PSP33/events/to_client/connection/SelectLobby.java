package it.polimi.ingsw.PSP33.events.to_client.connection;

import it.polimi.ingsw.PSP33.events.to_client.CCEvent;
import it.polimi.ingsw.PSP33.events.to_client.CCEventVisitor;

import java.util.Map;

public class SelectLobby implements CCEvent {

    private final Map<Integer, String> lobbies;

    public SelectLobby(Map<Integer, String> lobbies) {
        this.lobbies = lobbies;
    }

    public Map<Integer, String> getLobbies() {
        return lobbies;
    }

    @Override
    public void accept(CCEventVisitor ccEventVisitor) {
        ccEventVisitor.visit(this);
    }
}
