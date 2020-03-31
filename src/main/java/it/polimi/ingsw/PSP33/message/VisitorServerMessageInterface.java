package it.polimi.ingsw.PSP33.message;

import it.polimi.ingsw.PSP33.message.server.ServerMessage;

/**
 * Custom interface used to implement the visitor pattern for messages sent to server
 */
public interface VisitorServerMessageInterface {

    /**
     * Visit method for the visitor pattern
     * @param serverMessage it.polimi.ingsw.PSP33.message
     */
    void visit(ServerMessage serverMessage);
}
