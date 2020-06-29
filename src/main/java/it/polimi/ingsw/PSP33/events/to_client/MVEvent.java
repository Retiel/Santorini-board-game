package it.polimi.ingsw.PSP33.events.to_client;

/**
 * Custom interface used to implement the visitor pattern for visitable game events sent to the client
 */
public interface MVEvent{

    /**
     * Accept method of the visitor pattern
     * @param MVEventVisitor visitor instance that accepts the event
     */
    void accept(MVEventVisitor MVEventVisitor);
}
