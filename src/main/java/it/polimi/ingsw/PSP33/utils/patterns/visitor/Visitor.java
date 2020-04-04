package it.polimi.ingsw.PSP33.utils.patterns.visitor;

/**
 * Interface used to implement the visitor pattern, this is the core part of the selective gods effect
 */
public interface Visitor {

    /**
     * Visit method for the visitor pattern
     * @param visitableObject visit the defined class
     */
    void visit(VisitableObject visitableObject);
}
