package it.polimi.ingsw.PSP33.message;


/**
 * Custom interface used to implement the visitor pattern for visitable messages sent to client
 */
public interface VisitableClientMessageInterface {

    /**
     * Accept method of the visitor pattern
     * @param visitorClientMessageInterface it.polimi.ingsw.PSP33.message sent to client that needs to be accepted
     */
    void accept(VisitorClientMessageInterface visitorClientMessageInterface);
}
