package it.polimi.ingsw.PSP33.controller.godsRules.interfaces;

import it.polimi.ingsw.PSP33.controller.godsRules.implementation.Demeter;

public interface DemeterDefinition {

    /**
     * Method that define Demeter effect
     *
     * @param god class implementation
     */
    void visit(Demeter god);
}
