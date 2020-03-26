package message;

import message.client.ClientMessage;

/**
 * Interface used to implement the visitor pattern for messages sent to client
 */
public interface VisitorClientMessageInterface {

    /**
     * Visit method for the visitor pattern
     * @param clientMessage message
     */
    void visit(ClientMessage clientMessage);
}
