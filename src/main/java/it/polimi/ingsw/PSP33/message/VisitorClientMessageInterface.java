package it.polimi.ingsw.PSP33.message;

import it.polimi.ingsw.PSP33.message.client.ClientMessage;

/**
 * Custom interface used to implement the visitor pattern for messages sent to client
 */
public interface VisitorClientMessageInterface {

    /**
     * Visit method for the visitor pattern
     * @param clientMessage message
     */
    void visit(ClientMessage clientMessage);
}
