package it.polimi.ingsw.PSP33.message;

import it.polimi.ingsw.PSP33.message.client.ClientMessage;

/**
 * Interface used to implement the visitor pattern for messages sent to client
 */
public interface VisitorClientMessageInterface {

    /**
     * Visit method for the visitor pattern
     * @param clientMessage it.polimi.ingsw.PSP33.message
     */
    void visit(ClientMessage clientMessage);
}
