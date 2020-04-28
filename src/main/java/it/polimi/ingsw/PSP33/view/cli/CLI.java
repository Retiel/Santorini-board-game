package it.polimi.ingsw.PSP33.view.cli;

import it.polimi.ingsw.PSP33.events.toClient.MVEventSample;
import it.polimi.ingsw.PSP33.events.toClient.data.DataGrid;
import it.polimi.ingsw.PSP33.events.toClient.setup.PossiblePlacement;
import it.polimi.ingsw.PSP33.events.toClient.turn.*;
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

    @Override
    public void visit(DataGrid dataGrid) {

    }

    @Override
    public void visit(PossiblePlacement possiblePlacement) {

    }

    @Override
    public void visit(YouLose youLose) {

    }

    @Override
    public void visit(YouWin youWin) {

    }

    @Override
    public void visit(NewAction newAction) {

    }

    @Override
    public void visit(PossibleBuild possibleBuild) {

    }

    @Override
    public void visit(PossibleMove possibleMove) {

    }

    @Override
    public void visit(PossibleExtraAction possibleExtraAction) {

    }

    /*todo: methods called in the Client class for the implementation of the CLI manager*/
}
