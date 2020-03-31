package it.polimi.ingsw.PSP33.message.server;

<<<<<<< HEAD:src/main/java/message/server/TestMessage.java
import utils.patterns.visitor.Visitor;
=======
import it.polimi.ingsw.PSP33.message.VisitorServerMessageInterface;
>>>>>>> temp:src/main/java/it/polimi/ingsw/PSP33/message/server/TestMessage.java

/**
 * Basic implementation of the text from client to server
 * */
public class TestMessage extends ServerMessage {

    @Override
    public void accept(Visitor visitorServerMessageInterface) {
        visitorServerMessageInterface.visit(this);
    }
}
