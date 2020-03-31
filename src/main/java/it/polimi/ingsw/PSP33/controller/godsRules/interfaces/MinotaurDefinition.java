package it.polimi.ingsw.PSP33.controller.godsRules.interfaces;


import it.polimi.ingsw.PSP33.controller.godsRules.implementation.Minotaur;

public interface MinotaurDefinition {

    /**
     * Method that define Minotaur effect
     *
     * @param god class implementation
     */
    void visit(Minotaur god);
}
