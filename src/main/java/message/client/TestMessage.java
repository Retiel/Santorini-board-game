package message.client;

import utils.patterns.visitor.Visitor;

/**
 * Basic implementation of the text from server to client
 * */
public class TestMessage extends ClientMessage {

    @Override
    public void accept(Visitor visitorClientMessageInterface) {
        visitorClientMessageInterface.visit(this);
    }
}
