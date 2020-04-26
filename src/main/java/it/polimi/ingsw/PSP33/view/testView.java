package it.polimi.ingsw.PSP33.view;

import it.polimi.ingsw.PSP33.events.toClient.MVEventSample;
import it.polimi.ingsw.PSP33.events.toClient.data.DataModel;
import it.polimi.ingsw.PSP33.events.toClient.setup.PossiblePlacement;
import it.polimi.ingsw.PSP33.events.toClient.turn.*;

public class testView extends AbstractView {


    @Override
    public void visit(MVEventSample mvEventSample) {
        System.out.print("Success\n");
    }

    @Override
    public void visit(DataModel dataModel) {
        System.out.print("Data update...\n");
        System.out.print(dataModel.getModel().toString()+"\n");
        System.out.print("Data update finished\n");
    }

    @Override
    public void visit(PossiblePlacement possiblePlacement) {
        System.out.print("Placement update...\n");
        System.out.print(possiblePlacement.getCoordList().toString()+"\n");
        System.out.print("Placement update finished\n");
    }

    @Override
    public void visit(YouLose youLose) {

    }

    @Override
    public void visit(NewAction newAction) {
        System.out.print("New action update...\n");
        System.out.print(newAction.isMove()+"\n");
        System.out.print(newAction.isBuild()+"\n");
        System.out.print(newAction.isExtra()+"\n");
        System.out.print("New action update finished\n");
    }

    @Override
    public void visit(PossibleMove possibleMove) {
        System.out.print("Possible move update...\n");
        System.out.print(possibleMove.getCoordList().toString()+"\n");
        System.out.print(possibleMove.getGodsList().toString()+"\n");
        System.out.print("Possible move update finished\n");
    }

    @Override
    public void visit(PossibleBuild possibleBuild) {
        System.out.print("Possible build update...\n");
        System.out.print(possibleBuild.getCoordList().toString()+"\n");
        System.out.print(possibleBuild.getGodsList().toString()+"\n");
        System.out.print("Possible build update finished\n");
    }

    @Override
    public void visit(PossibleExtraAction possibleExtraAction) {

    }
}
