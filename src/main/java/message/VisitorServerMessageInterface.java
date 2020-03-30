package message;

import message.server.ServerMessage;

/**
 * Custom interface used to implement the visitor pattern for messages sent to server
 */
public interface VisitorServerMessageInterface {

    /**
     * Visit method for the visitor pattern
     * @param serverMessage message
     */
    void visit(ServerMessage serverMessage);
}
