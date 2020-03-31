package it.polimi.ingsw.PSP33.utils.patterns.visitor;


public interface VisitorDefinition {

    /**
     * Visit method for the visitor pattern
     * @param visitableObject visit the defined class
     */
    void visit(VisitableObject visitableObject);
}
