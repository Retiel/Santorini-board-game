package it.polimi.ingsw.PSP33.events.toServer;

import it.polimi.ingsw.PSP33.events.VCEventVisitor;

/**
 * Custom interface used to implement the visitor pattern for visitable messages sent to server
 */
public interface VCEvent {

    /**
     * Accept method of the visitor pattern
     * @param VCEventVisitor message sent to server that needs to be accepted
     */
    void accept(VCEventVisitor VCEventVisitor);
}
