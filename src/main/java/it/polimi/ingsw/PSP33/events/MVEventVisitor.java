package it.polimi.ingsw.PSP33.events;

import it.polimi.ingsw.PSP33.events.mvevents.MVEventSample;

/**
 * Custom interface used to implement the visitor pattern for messages sent to client
 */
public interface MVEventVisitor {

    //TODO: add visit method for each MVEvent
    void visit(MVEventSample mvEventSample);
}
