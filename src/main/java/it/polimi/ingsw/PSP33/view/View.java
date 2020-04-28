package it.polimi.ingsw.PSP33.view;

import it.polimi.ingsw.PSP33.events.toClient.MVEvent;
import it.polimi.ingsw.PSP33.events.toClient.MVEventSample;
import it.polimi.ingsw.PSP33.events.toClient.data.DataGrid;
import it.polimi.ingsw.PSP33.events.toClient.setup.PossiblePlacement;
import it.polimi.ingsw.PSP33.events.toClient.turn.*;

/**
 * Implementation of the visitor pattern
 * */
public class View extends AbstractView{

    @Override
    public void update(MVEvent mvEvent) {
        mvEvent.accept(this);
    }

    @Override
    public void visit(MVEventSample mvEventSample) {
        System.out.print("Success\n");
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
}
