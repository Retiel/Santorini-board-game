package it.polimi.ingsw.PSP33.view;

import it.polimi.ingsw.PSP33.events.toClient.MVEvent;
import it.polimi.ingsw.PSP33.events.toClient.data.DataCell;
import it.polimi.ingsw.PSP33.events.toClient.data.DataGrid;
import it.polimi.ingsw.PSP33.events.toClient.data.DataPawn;
import it.polimi.ingsw.PSP33.events.toClient.data.DataPlayer;
import it.polimi.ingsw.PSP33.events.toClient.setup.PossiblePlacement;
import it.polimi.ingsw.PSP33.events.toClient.turn.*;

/**
 * Implementation of the visitor pattern
 * */
public class View extends AbstractView {

    @Override
    public void update(MVEvent mvEvent) {
        mvEvent.accept(this);
    }

    @Override
    public void visit(DataGrid dataGrid) {

    }

    @Override
    public void visit(DataCell dataCell) {

    }

    @Override
    public void visit(DataPlayer dataPlayer) {

    }

    @Override
    public void visit(DataPawn dataPawn) {

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
