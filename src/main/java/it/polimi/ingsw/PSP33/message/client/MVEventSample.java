package it.polimi.ingsw.PSP33.message.client;

import it.polimi.ingsw.PSP33.message.MVEventVisitor;

/**
 * Basic implementation of the text from server to client
 * */
public class MVEventSample extends MVEvent {

    @Override
    public void accept(MVEventVisitor MVEventVisitor) {
        MVEventVisitor.visit(this);
    }
}
