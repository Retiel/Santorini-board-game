package utils.patterns.visitor;

/**
 * Interface used to implement the visitor pattern
 */
public interface Visitable {

    /**
     * Accept method of the visitor pattern
     * @param visitor
     */
    void accept(Visitor visitor);
}
