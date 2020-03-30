package controller.godsRules.interfaces;

import controller.godsRules.implementation.Artemis;

public interface ArtemisDefinition{

    /**
     * Method that define Artemis effect
     *
     * @param god class implementation
     */
    void visit(Artemis god) ;
}
