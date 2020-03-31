package it.polimi.ingsw.PSP33.controller.godsRules.interfaces;

import it.polimi.ingsw.PSP33.controller.godsRules.implementation.Apollo;

public interface ApolloDefinition {

    /**
     * Method that define Apollo effect
     *
     * @param apollo class implementation
     */
    void visit(Apollo apollo) ;
}