package it.polimi.ingsw.PSP33.view.gui;

import it.polimi.ingsw.PSP33.events.to_client.data.DataBoard;
import it.polimi.ingsw.PSP33.events.to_client.data.DataCell;
import it.polimi.ingsw.PSP33.events.to_client.data.DataPlayer;
import it.polimi.ingsw.PSP33.events.to_client.setup.CurrentPlayer;
import it.polimi.ingsw.PSP33.events.to_client.setup.PossiblePlacement;
import it.polimi.ingsw.PSP33.events.to_client.setup.SelectGods;
import it.polimi.ingsw.PSP33.events.to_client.setup.YourGod;
import it.polimi.ingsw.PSP33.events.to_client.turn.*;
import it.polimi.ingsw.PSP33.events.to_server.setup.ChoosenGod;
import it.polimi.ingsw.PSP33.events.to_server.setup.PlacePawn;
import it.polimi.ingsw.PSP33.events.to_server.setup.SelectedGods;
import it.polimi.ingsw.PSP33.events.to_server.turn.*;
import it.polimi.ingsw.PSP33.model.God;
import it.polimi.ingsw.PSP33.model.light_version.LightModel;
import it.polimi.ingsw.PSP33.utils.Coord;
import it.polimi.ingsw.PSP33.view.AbstractView;

import javax.swing.SwingUtilities;
import java.util.List;

public class GUI extends AbstractView implements ButtonListener {

    private LightModel lightModel;
    private MainFrame mainFrame;

    private enum Event {
        PLACE_PAWN,
        BUILD_ACTION,
        MOVE_ACTION,
        EXTRA_ACTION
    }
    private Event nextEvent;

    //TODO: MainFrame
    private boolean trigger;


    public GUI() {
        SwingUtilities.invokeLater(() -> {
            lightModel = new LightModel();

            mainFrame = new MainFrame();
            setButtonListener();
        });
    }

    @Override
    public void visit(DataBoard dataBoard) {
        SwingUtilities.invokeLater(() -> {
            mainFrame.setVisible(true);
            lightModel.setLightGrid(dataBoard.getGrid().getGrid());
        });

    }

    @Override
    public void visit(DataCell dataCell) {
        SwingUtilities.invokeLater(() -> {
            if (dataCell.getOldCell() != null){
                int oldx = dataCell.getOldCell().getCoord().getX();
                int oldy = dataCell.getOldCell().getCoord().getY();
                lightModel.getLightGrid()[oldx][oldy] = dataCell.getOldCell();

                mainFrame.setButton(dataCell.getOldCell());
            }

            int newx = dataCell.getNewCell().getCoord().getX();
            int newy = dataCell.getNewCell().getCoord().getY();
            lightModel.getLightGrid()[newx][newy] = dataCell.getNewCell();

            mainFrame.setButton(dataCell.getNewCell());
        });

    }

    @Override
    public void visit(DataPlayer dataPlayer) {
        SwingUtilities.invokeLater(() -> {
            lightModel.setPlayers(dataPlayer.getPlayer());
            lightModel.setPlayerName(dataPlayer.getName());
        });

    }

    @Override
    public void visit(YourGod yourGod) {
        SwingUtilities.invokeLater(() -> {
            God chosenGod = mainFrame.selectGod(yourGod.getGods());

            ChoosenGod choosenGod = new ChoosenGod(chosenGod);
            notifyObservers(choosenGod);
        });
    }

    @Override
    public void visit(CurrentPlayer currentPlayer) {
        SwingUtilities.invokeLater(() -> mainFrame.setText(currentPlayer.getName() + "'s turn"));
    }

    @Override
    public void visit(PossiblePlacement possiblePlacement) {
        SwingUtilities.invokeLater(() -> {
            nextEvent = Event.PLACE_PAWN;
            mainFrame.setText("Where do you want to place your worker?");

            mainFrame.enableButtons(possiblePlacement.getCoordList(), null);
        });
    }

    @Override
    public void visit(SelectGods selectGods) {
        SwingUtilities.invokeLater(() -> {
            List<God> chosenGods = mainFrame.selectGods(selectGods.getGods(), selectGods.getNumberOfGods());
            SelectedGods selectedGods = new SelectedGods(chosenGods);

            notifyObservers(selectedGods);
        });

    }

    @Override
    public void visit(YouLose youLose) {
        SwingUtilities.invokeLater(() -> mainFrame.showLose(youLose.getName()));
    }

    @Override
    public void visit(YouWin youWin) {
        SwingUtilities.invokeLater(() -> mainFrame.showWin());
    }

    @Override
    public void visit(SelectPawn selectPawn) {
        SwingUtilities.invokeLater(() -> {
            switch (selectPawn.getValue()) {
                default:
                    int i = mainFrame.selectWorker();
                    notifyObservers(new SelectedPawn(i));
                    break;

                case 1:
                    mainFrame.setText("You can move only the worker number 1");
                    notifyObservers(new SelectedPawn(1));
                    break;

                case 2:
                    mainFrame.setText("You can move only the worker number 2");
                    notifyObservers(new SelectedPawn(2));
                    break;
            }
        });
    }

    @Override
    public void visit(NewAction newAction) {
        SwingUtilities.invokeLater(() -> {
            int j;

            RequestPossibleBuild rpb;
            RequestPossibleMove rpm;
            RequestExtraAction rea;

            if (!newAction.isExtra() && !newAction.isBuild() && !newAction.isMove()){
                NewTurn newTurn = new NewTurn();
                notifyObservers(newTurn);

                mainFrame.setText("End of the turn");
                lightModel.setYourTurn(false);
            } else {

                lightModel.setYourTurn(true);
                //decide action with the Boolean and send input to controller (switch case)
                if (newAction.isMove()){
                    if (!newAction.isExtra()){
                        //send move action to controller
                        rpm = new RequestPossibleMove();
                        notifyObservers(rpm);
                    } else {
                        //choose and create possible move or extra message to notify to the controller
                        j = mainFrame.selectMove();

                        if (j == 1){
                            rpm = new RequestPossibleMove();
                            notifyObservers(rpm);
                        } else if (j == 2){
                            rea = new RequestExtraAction();
                            notifyObservers(rea);
                        }
                    }
                }

                if (newAction.isBuild()){
                    if (!newAction.isExtra()){
                        //send build action to controller
                        rpb = new RequestPossibleBuild();
                        notifyObservers(rpb);
                    } else{
                        //choose your action and send proper message to server
                        j = mainFrame.selectBuild();

                        if (j == 1){
                            rpb = new RequestPossibleBuild();
                            notifyObservers(rpb);
                        } else if (j == 2){
                            rea = new RequestExtraAction();
                            notifyObservers(rea);
                        }
                    }
                }

                if (newAction.isExtra()){
                    if (!newAction.isBuild()&&!newAction.isMove()){
                        rea = new RequestExtraAction();
                        notifyObservers(rea);
                    }
                }
            }
        });
    }

    @Override
    public void visit(PossibleBuild possibleBuild) {
        SwingUtilities.invokeLater(() -> {
            trigger = possibleBuild.isRoofAvailable();

            nextEvent = Event.BUILD_ACTION;
            mainFrame.setText("Where do you want to build your Block?");

            mainFrame.enableButtons(possibleBuild.getCoordList(), possibleBuild.getGodsList());
        });

    }

    @Override
    public void visit(PossibleMove possibleMove) {
        SwingUtilities.invokeLater(() -> {
            nextEvent = Event.MOVE_ACTION;
            mainFrame.setText("Where do you want to move your worker?");

            mainFrame.enableButtons(possibleMove.getCoordList(), possibleMove.getGodsList());
        });

    }

    @Override
    public void visit(PossibleExtraAction possibleExtraAction) {
        SwingUtilities.invokeLater(() -> {
            nextEvent = Event.EXTRA_ACTION;
            mainFrame.setText("Where do you want to use Your God Action?");

            mainFrame.enableButtons(possibleExtraAction.getCoordList(), null);
        });
    }


    @Override
    public void update(Coord coord) {
        SwingUtilities.invokeLater(() -> {
            mainFrame.disableButtons();
            switch (nextEvent) {
                case PLACE_PAWN:
                    notifyObservers(new PlacePawn(coord));
                    break;

                case BUILD_ACTION:
                    boolean b = false;
                    if(trigger) {
                        int i = mainFrame.selectRoof();

                        if(i == 0) {
                            b = true;
                        }
                    }
                    notifyObservers(new BuildAction(coord, b));
                    break;

                case MOVE_ACTION:
                    notifyObservers(new MoveAction(coord));
                    break;

                case EXTRA_ACTION:
                    notifyObservers(new ExtraAction(coord));
                    break;
            }
        });
    }

    private void setButtonListener() {
        for(CellButton[] buttonsRow : mainFrame.getButtons()) {
            for(CellButton button :buttonsRow) {
                button.addButtonListener(this);
            }
        }
    }
}
