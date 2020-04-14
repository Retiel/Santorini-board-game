package it.polimi.ingsw.PSP33.events;

/**
 * Custom interface used to implement the visitor pattern for visitable messages sent to server
 */
public interface VCEventVisitable {

    /**
     * Accept method of the visitor pattern
     * @param VCEventVisitor message sent to server that needs to be accepted
     */
    void accept(VCEventVisitor VCEventVisitor);
}
