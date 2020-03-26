package message.client;

import message.VisitorClientMessageInterface;

public class TestMessage extends ClientMessage {

    @Override
    public void accept(VisitorClientMessageInterface visitorClientMessageInterface) {
        visitorClientMessageInterface.visit(this);
    }
}
