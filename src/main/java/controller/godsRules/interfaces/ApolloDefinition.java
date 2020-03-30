package controller.godsRules.interfaces;

import controller.godsRules.implementation.Apollo;

public interface ApolloDefinition {

    /**
     * Method that define Apollo effect
     *
     * @param apollo class implementation
     */
    void visit(Apollo apollo) ;
}