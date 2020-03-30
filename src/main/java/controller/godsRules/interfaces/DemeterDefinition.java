package controller.godsRules.interfaces;

import controller.godsRules.implementation.Demeter;

public interface DemeterDefinition {

    /**
     * Method that define Demeter effect
     *
     * @param god class implementation
     */
    void visit(Demeter god);
}
