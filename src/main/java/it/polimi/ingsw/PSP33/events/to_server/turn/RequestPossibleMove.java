package it.polimi.ingsw.PSP33.events.to_server.turn;

import it.polimi.ingsw.PSP33.events.to_server.VCEventVisitor;
import it.polimi.ingsw.PSP33.events.to_server.VCEvent;

/**
 * Request possible move event
 */
public class RequestPossibleMove implements VCEvent {

    @Override
    public void accept(VCEventVisitor VCEventVisitor) {
        VCEventVisitor.visit(this);
    }
}
