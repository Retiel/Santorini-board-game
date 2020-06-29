package it.polimi.ingsw.PSP33.events.to_server;

/**
 * Custom interface used to implement the visitor pattern for visitable setup events sent to the server
 */
public interface SCEvent {

    /**
     * Accept method of the visitor pattern
     * @param scEventVisitor visitor instance that accepts the event
     */
    void accept(SCEventVisitor scEventVisitor);
}
