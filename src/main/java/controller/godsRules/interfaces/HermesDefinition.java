package controller.godsRules.interfaces;

import controller.godsRules.implementation.Hermes;

public interface HermesDefinition {

    /**
     * Method that define Hermes effect
     *
     * @param god class implementation
     */
    void visit(Hermes god);
}
