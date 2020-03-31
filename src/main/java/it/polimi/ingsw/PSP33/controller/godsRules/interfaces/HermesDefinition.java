package it.polimi.ingsw.PSP33.controller.godsRules.interfaces;

import it.polimi.ingsw.PSP33.controller.godsRules.implementation.Hermes;

public interface HermesDefinition {

    /**
     * Method that define Hermes effect
     *
     * @param god class implementation
     */
    void visit(Hermes god);
}
