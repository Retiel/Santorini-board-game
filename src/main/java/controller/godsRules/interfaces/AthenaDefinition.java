package controller.godsRules.interfaces;

import controller.godsRules.implementation.Athena;

public interface AthenaDefinition {

    /**
     * Method that define Athena effect
     *
     * @param god class implementation
     */
    void visit(Athena god);
}
