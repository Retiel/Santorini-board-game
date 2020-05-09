package it.polimi.ingsw.PSP33.events.toClient;

import it.polimi.ingsw.PSP33.events.MVEventVisitor;

/**
 * Custom interface used to implement the visitor pattern for visitable messages sent to client
 */
public interface MVEvent{

    /**
     * Accept method of the visitor pattern
     * @param MVEventVisitor message sent to client that needs to be accepted
     */
    void accept(MVEventVisitor MVEventVisitor);
}
