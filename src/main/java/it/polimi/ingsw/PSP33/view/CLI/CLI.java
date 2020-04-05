package it.polimi.ingsw.PSP33.view.CLI;

import it.polimi.ingsw.PSP33.view.AbstractView;

/**
 * the rapppresentation of the View Class for the CLI mode
 */
public class CLI extends AbstractView {

    private InOutCLI consoleCLI;
    private MenuManager menuManager;
    public CLI(InOutCLI io, MenuManager menuManager){
        this.consoleCLI = io;
        this.menuManager = menuManager;
    }

    /*todo: methods called in the Client class for the implementation of the CLI manager*/
}
