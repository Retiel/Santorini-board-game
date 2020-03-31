package it.polimi.ingsw.PSP33.utils.patterns.visitor;

/**
 * Interface used to implement the visitor pattern
 */
public interface Visitable {

    /**
     * Accept method of the visitor pattern
     * @param visitor accept the defined class
     */
    void accept(Visitor visitor);
}
