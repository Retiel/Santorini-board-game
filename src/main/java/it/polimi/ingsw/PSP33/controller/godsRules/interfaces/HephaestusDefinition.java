package it.polimi.ingsw.PSP33.controller.godsRules.interfaces;

import it.polimi.ingsw.PSP33.controller.godsRules.implementation.Hephaestus;

public interface HephaestusDefinition {

    /**
     * Method that define Hephaestus effect
     *
     * @param god class implementation
     */
    void visit(Hephaestus god);
}
