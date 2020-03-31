package it.polimi.ingsw.PSP33.message.client;

<<<<<<< HEAD:src/main/java/message/client/TestMessage.java
import utils.patterns.visitor.Visitor;
=======
import it.polimi.ingsw.PSP33.message.VisitorClientMessageInterface;
>>>>>>> temp:src/main/java/it/polimi/ingsw/PSP33/message/client/TestMessage.java

/**
 * Basic implementation of the text from server to client
 * */
public class TestMessage extends ClientMessage {

    @Override
    public void accept(Visitor visitorClientMessageInterface) {
        visitorClientMessageInterface.visit(this);
    }
}
