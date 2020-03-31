package it.polimi.ingsw.PSP33.message.client;

import it.polimi.ingsw.PSP33.message.VisitorClientMessageInterface;

/**
 * Basic implementation of the text from server to client
 * */
public class TestMessage extends ClientMessage {

    @Override
    public void accept(VisitorClientMessageInterface visitorClientMessageInterface) {
        visitorClientMessageInterface.visit(this);
    }
}
