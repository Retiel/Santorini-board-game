package message.client;

import message.VisitorClientMessageInterface;

/**
 * Basic implementation of the text from server to client
 * */
public class TestMessage extends ClientMessage {

    @Override
    public void accept(VisitorClientMessageInterface visitorClientMessageInterface) {
        visitorClientMessageInterface.visit(this);
    }
}
