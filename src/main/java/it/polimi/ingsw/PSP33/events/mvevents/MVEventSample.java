package it.polimi.ingsw.PSP33.events.mvevents;

import it.polimi.ingsw.PSP33.events.MVEventVisitor;

/**
 * Basic implementation of the text from server to client
 * */
public class MVEventSample extends MVEvent {

    @Override
    public void accept(MVEventVisitor MVEventVisitor) {
        MVEventVisitor.visit(this);
    }
}
