package it.polimi.ingsw.PSP33.events;

import it.polimi.ingsw.PSP33.events.vcevent.setup.PlacePawn;
import it.polimi.ingsw.PSP33.events.vcevent.VCEventSample;

/**
 * Custom interface used to implement the visitor pattern for messages sent to server
 */
public interface VCEventVisitor {

    //TODO: add visit method for each VCEvent
    void visit(VCEventSample vcEventSample);

    void visit(PlacePawn placePawn);
}
