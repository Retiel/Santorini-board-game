package it.polimi.ingsw.PSP33.message;

import it.polimi.ingsw.PSP33.message.server.VCEventSample;

/**
 * Custom interface used to implement the visitor pattern for messages sent to server
 */
public interface VCEventVisitor {

    //TODO: add visit method for each VCEvent
    void visit(VCEventSample vcEventSample);
}
