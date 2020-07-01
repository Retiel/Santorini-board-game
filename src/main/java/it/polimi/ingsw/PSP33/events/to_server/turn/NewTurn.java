package it.polimi.ingsw.PSP33.events.to_server.turn;

import it.polimi.ingsw.PSP33.events.to_server.VCEventVisitor;
import it.polimi.ingsw.PSP33.events.to_server.VCEvent;

/**
 * New turn event
 */
public class NewTurn implements VCEvent {

    @Override
    public void accept(VCEventVisitor VCEventVisitor) {
        VCEventVisitor.visit(this);
    }
}
