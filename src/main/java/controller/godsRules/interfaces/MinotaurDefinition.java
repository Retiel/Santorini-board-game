package controller.godsRules.interfaces;


import controller.godsRules.implementation.Minotaur;

public interface MinotaurDefinition {

    /**
     * Method that define Minotaur effect
     *
     * @param god class implementation
     */
    void visit(Minotaur god);
}
