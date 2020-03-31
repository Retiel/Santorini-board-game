package it.polimi.ingsw.PSP33.message;

/**
 * Interface used to implement the visitor pattern for visitable messages sent to server
 */
public interface VisitableServerMessageInterface {

    /**
     * Accept method of the visitor pattern
     * @param visitorServerMessageInterface it.polimi.ingsw.PSP33.message sent to server that needs to be accepted
     */
    void accept(VisitorServerMessageInterface visitorServerMessageInterface);
}
