package it.polimi.ingsw.PSP33.events.to_client;

/**
 * Custom interface used to implement the visitor pattern for visitable setup events sent to the client
 */
public interface CCEvent {

    /**
     * Accept method of the visitor pattern
     * @param ccEventVisitor visitor instance that accepts the event
     */
    void accept(CCEventVisitor ccEventVisitor);
}
