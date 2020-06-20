package it.polimi.ingsw.PSP33.view.gui;

import it.polimi.ingsw.PSP33.events.toClient.data.DataBoard;
import it.polimi.ingsw.PSP33.events.toClient.data.DataCell;
import it.polimi.ingsw.PSP33.events.toClient.data.DataPlayer;
import it.polimi.ingsw.PSP33.events.toClient.setup.CurrentPlayer;
import it.polimi.ingsw.PSP33.events.toClient.setup.PossiblePlacement;
import it.polimi.ingsw.PSP33.events.toClient.setup.SelectGods;
import it.polimi.ingsw.PSP33.events.toClient.setup.YourGod;
import it.polimi.ingsw.PSP33.events.toClient.turn.*;
import it.polimi.ingsw.PSP33.model.light_version.LightModel;
import it.polimi.ingsw.PSP33.view.AbstractView;

import javax.swing.*;

public class GUI extends AbstractView {

    private LightModel lightModel;
    private MainFrame mainFrame;

    public GUI() {
        lightModel = new LightModel();

        SwingUtilities.invokeLater(() -> mainFrame = new MainFrame());
    }

    @Override
    public void visit(DataBoard dataBoard) {
        lightModel.setLightGrid(dataBoard.getGrid().getGrid());
    }

    @Override
    public void visit(DataCell dataCell) {
        if (dataCell.getOldCell() != null){
            int oldx = dataCell.getOldCell().getCoord().getX();
            int oldy = dataCell.getOldCell().getCoord().getY();
            lightModel.getLightGrid()[oldx][oldy] = dataCell.getOldCell();
        }

        int newx = dataCell.getNewCell().getCoord().getX();
        int newy = dataCell.getNewCell().getCoord().getY();
        lightModel.getLightGrid()[newx][newy] = dataCell.getNewCell();
    }

    @Override
    public void visit(DataPlayer dataPlayer) {
        lightModel.setPlayers(dataPlayer.getPlayer());
        lightModel.setPlayerName(dataPlayer.getName());
    }

    @Override
    public void visit(YourGod yourGod) {

    }

    @Override
    public void visit(CurrentPlayer currentPlayer) {

    }

    @Override
    public void visit(PossiblePlacement possiblePlacement) {

    }

    @Override
    public void visit(SelectGods selectGods) {

    }

    @Override
    public void visit(YouLose youLose) {

    }

    @Override
    public void visit(YouWin youWin) {

    }

    @Override
    public void visit(SelectPawn selectPawn) {

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
