package utils.patterns.visitor;


public interface VisitorDefinition {

    /**
     * Visit method for the visitor pattern
     * @param visitableObject
     */
    void visit(VisitableObject visitableObject);
}
