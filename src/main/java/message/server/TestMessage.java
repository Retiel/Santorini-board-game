package message.server;

import utils.patterns.visitor.Visitor;

/**
 * Basic implementation of the text from client to server
 * */
public class TestMessage extends ServerMessage {

    @Override
    public void accept(Visitor visitorServerMessageInterface) {
        visitorServerMessageInterface.visit(this);
    }
}
