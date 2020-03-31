package utils.patterns.visitor;

/**
 * Interface standard format, use as a sample to create new interfaces using the visitor pattern
 *
 * */
public interface VisitorDefinition {

    /**
     * Visit method for the visitor pattern
     * @param visitableObject the object usign the pattern
     */
    void visit(VisitableObject visitableObject);
}
