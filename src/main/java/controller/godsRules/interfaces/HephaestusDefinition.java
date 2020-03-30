package controller.godsRules.interfaces;

import controller.godsRules.implementation.Hephaestus;

public interface HephaestusDefinition {

    /**
     * Method that define Hephaestus effect
     *
     * @param god class implementation
     */
    void visit(Hephaestus god);
}
