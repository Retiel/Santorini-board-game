package message;

/**
 * Custom interface used to implement the visitor pattern for visitable messages sent to server
 */
public interface VisitableServerMessageInterface {

    /**
     * Accept method of the visitor pattern
     * @param visitorServerMessageInterface message sent to server that needs to be accepted
     */
    void accept(VisitorServerMessageInterface visitorServerMessageInterface);
}
