package it.polimi.ingsw.PSP33.view.cli;

import it.polimi.ingsw.PSP33.events.toClient.MVEventSample;
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

    @Override
    public void visit(MVEventSample mvEventSample) {

    }

    /*todo: methods called in the Client class for the implementation of the CLI manager*/
}
