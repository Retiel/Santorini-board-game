package controller.godsRules;

/**
 * Basic Interface template for gods effect
 *
 * @author rayol
 * */
public interface GodEffect {

    /**
     * Method to verify that the action is within the controller.rules set by the gods
     *
     * */
    boolean verifyRule();
}
