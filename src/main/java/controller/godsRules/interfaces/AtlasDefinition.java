package controller.godsRules.interfaces;

import controller.godsRules.implementation.Atlas;

public interface AtlasDefinition{

    /**
     * Method that define Atlas effect
     *
     * @param god class implementation
     */
    void visit(Atlas god) ;
}
