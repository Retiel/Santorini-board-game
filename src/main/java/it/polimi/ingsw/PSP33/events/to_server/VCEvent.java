package it.polimi.ingsw.PSP33.events.to_server;

/**
 * Custom interface used to implement the visitor pattern for visitable game events sent to the server
 */
public interface VCEvent {

    /**
     * Accept method of the visitor pattern
     * @param VCEventVisitor visitor instance that accepts the event
     */
    void accept(VCEventVisitor VCEventVisitor);
}
