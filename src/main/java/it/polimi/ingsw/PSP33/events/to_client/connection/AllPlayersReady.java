package it.polimi.ingsw.PSP33.events.to_client.connection;

import it.polimi.ingsw.PSP33.events.to_client.CCEvent;
import it.polimi.ingsw.PSP33.events.to_client.CCEventVisitor;

public class AllPlayersReady implements CCEvent {
    @Override
    public void accept(CCEventVisitor ccEventVisitor) {
        ccEventVisitor.visit(this);
    }
}
