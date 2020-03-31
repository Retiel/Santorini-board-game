package it.polimi.ingsw.PSP33.controller.godsRules.interfaces;

import it.polimi.ingsw.PSP33.controller.godsRules.implementation.Atlas;

public interface AtlasDefinition{

    /**
     * Method that define Atlas effect
     *
     * @param god class implementation
     */
    void visit(Atlas god) ;
}
