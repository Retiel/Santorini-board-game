package message.server;

import message.VisitorServerMessageInterface;

public class TestMessage extends ServerMessage {

    @Override
    public void accept(VisitorServerMessageInterface visitorServerMessageInterface) {
        visitorServerMessageInterface.visit(this);
    }
}
