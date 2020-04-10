package it.polimi.ingsw.PSP33.message.server;

import it.polimi.ingsw.PSP33.message.VCEventVisitor;

/**
 * Basic implementation of the text from client to server
 * */
public class VCEventSample extends VCEvent {

    @Override
    public void accept(VCEventVisitor VCEventVisitor) {
        VCEventVisitor.visit(this);
    }
}
