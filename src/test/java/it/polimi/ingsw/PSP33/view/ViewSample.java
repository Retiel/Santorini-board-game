package it.polimi.ingsw.PSP33.view;

import it.polimi.ingsw.PSP33.events.toClient.data.DataCell;
import it.polimi.ingsw.PSP33.events.toClient.data.DataBoard;
import it.polimi.ingsw.PSP33.events.toClient.data.DataPawn;
import it.polimi.ingsw.PSP33.events.toClient.data.DataPlayer;
import it.polimi.ingsw.PSP33.events.toClient.setup.CurrentPlayer;
import it.polimi.ingsw.PSP33.events.toClient.setup.PossiblePlacement;
import it.polimi.ingsw.PSP33.events.toClient.setup.SelectGods;
import it.polimi.ingsw.PSP33.events.toClient.setup.YourGod;
import it.polimi.ingsw.PSP33.events.toClient.turn.*;

public class ViewSample extends AbstractView {

    @Override
    public void visit(DataBoard dataBoard) {
        System.out.print("Data update...\n");
        System.out.print(dataBoard.getGrid().toString()+"\n");
        System.out.print("Data update finished\n\n\n");
    }

    @Override
    public void visit(DataCell dataCell) {
        System.out.print("Data update...\n");
        System.out.print(dataCell.getOldCell().toString()+"\n");
        System.out.print(dataCell.getNewCell().toString()+"\n");
        System.out.print("Data update finished\n\n\n");
    }

    @Override
    public void visit(DataPlayer dataPlayer) {
        System.out.print("Data update...\n");
        System.out.print(dataPlayer.toString()+"\n");
        System.out.print("Data update finished\n\n\n");
    }

    @Override
    public void visit(DataPawn dataPawn) {
        System.out.print("Data update...\n");
        System.out.print(dataPawn.toString()+"\n");
        System.out.print("Data update finished\n\n\n");
    }

    @Override
    public void visit(YourGod yourGod) {
        System.out.print("Gods update...\n");
        System.out.print(yourGod.toString()+"\n");
        System.out.print("Gods finished\n\n\n");
    }

    @Override
    public void visit(CurrentPlayer currentPlayer) {
        /* Never received by the clients */
        System.out.print("Unexpected message coming from the sinkhole\n\n\n");
    }

    @Override
    public void visit(PossiblePlacement possiblePlacement) {
        System.out.print("Placement update...\n");
        System.out.print(possiblePlacement.getCoordList().toString()+"\n");
        System.out.print("Placement update finished\n\n\n");
    }

    @Override
    public void visit(SelectGods selectGods) {
        System.out.print("SelectedGods update...\n");
        System.out.print(selectGods.toString()+"\n");
        System.out.print("SelectedGods finished\n\n\n");
    }

    @Override
    public void visit(YouLose youLose) {
        /* Missing check */
    }

    @Override
    public void visit(YouWin youWin) {
        /* Missing check */
    }

    @Override
    public void visit(NewAction newAction) {
        System.out.print("New action update...\n");
        System.out.print(newAction.isMove()+"\n");
        System.out.print(newAction.isBuild()+"\n");
        System.out.print(newAction.isExtra()+"\n");
        System.out.print("New action update finished\n\n\n");
    }

    @Override
    public void visit(PossibleMove possibleMove) {
        System.out.print("Possible move update...\n");
        System.out.print(possibleMove.getCoordList().toString()+"\n");
        System.out.print(possibleMove.getGodsList().toString()+"\n");
        System.out.print("Possible move update finished\n\n\n");
    }

    @Override
    public void visit(PossibleBuild possibleBuild) {
        System.out.print("Possible build update...\n");
        System.out.print(possibleBuild.getCoordList().toString()+"\n");
        System.out.print(possibleBuild.getGodsList().toString()+"\n");
        System.out.print("Possible build update finished\n\n\n");
    }

    @Override
    public void visit(PossibleExtraAction possibleExtraAction) {
        System.out.print("Possible extra action update...\n");
        System.out.print(possibleExtraAction.getCoordList().toString()+"\n");
        System.out.print("Possible extra action update finished\n\n\n");
    }
}
