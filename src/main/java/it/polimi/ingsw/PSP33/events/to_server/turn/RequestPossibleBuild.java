package it.polimi.ingsw.PSP33.events.to_server.turn;

import it.polimi.ingsw.PSP33.events.to_server.VCEvent;
import it.polimi.ingsw.PSP33.events.to_server.VCEventVisitor;

/**
 * Request possible build event
 */
public class RequestPossibleBuild implements VCEvent {

    @Override
    public void accept(VCEventVisitor VCEventVisitor) {
        VCEventVisitor.visit(this);
    }
}
