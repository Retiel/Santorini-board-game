package it.polimi.ingsw.PSP33.controller.godsRules.interfaces;

import it.polimi.ingsw.PSP33.controller.godsRules.implementation.Athena;

public interface AthenaDefinition {

    /**
     * Method that define Athena effect
     *
     * @param god class implementation
     */
    void visit(Athena god);
}
