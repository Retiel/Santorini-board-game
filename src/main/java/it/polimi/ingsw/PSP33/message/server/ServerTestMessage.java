package it.polimi.ingsw.PSP33.message.server;


import it.polimi.ingsw.PSP33.message.VisitorServerMessageInterface;

/**
 * Basic implementation of the text from client to server
 * */
public class ServerTestMessage extends ServerMessage {

    @Override
    public void accept(VisitorServerMessageInterface visitorServerMessageInterface) {
        visitorServerMessageInterface.visit(this);
    }
}
