package it.polimi.ingsw.PSP33.message;

import utils.patterns.visitor.Visitable;
import utils.patterns.visitor.Visitor;

/**
 * Custom interface used to implement the visitor pattern for visitable messages sent to client
 */
public interface VisitableClientMessageInterface extends Visitable {

    /**
     * Accept method of the visitor pattern
     * @param visitorClientMessageInterface it.polimi.ingsw.PSP33.message sent to client that needs to be accepted
     */
    @Override
    void accept(Visitor visitorClientMessageInterface);
}
