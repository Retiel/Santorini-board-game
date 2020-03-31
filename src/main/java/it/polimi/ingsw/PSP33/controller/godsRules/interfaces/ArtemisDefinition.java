package it.polimi.ingsw.PSP33.controller.godsRules.interfaces;

import it.polimi.ingsw.PSP33.controller.godsRules.implementation.Artemis;

public interface ArtemisDefinition{

    /**
     * Method that define Artemis effect
     *
     * @param god class implementation
     */
    void visit(Artemis god) ;
}
