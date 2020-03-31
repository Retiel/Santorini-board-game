package message;

import utils.patterns.visitor.Visitable;
import utils.patterns.visitor.Visitor;

/**
 * Custom interface used to implement the visitor pattern for visitable messages sent to server
 */
public interface VisitableServerMessageInterface extends Visitable {

    /**
     * Accept method of the visitor pattern
     * @param visitorServerMessageInterface message sent to server that needs to be accepted
     */
    @Override
    void accept(Visitor visitorServerMessageInterface);
}
