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
import it.polimi.ingsw.PSP33.view.gui.components.ButtonListener;
import it.polimi.ingsw.PSP33.view.gui.components.CellButton;

import javax.swing.SwingUtilities;
import java.util.List;

public class GUI extends AbstractView implements ButtonListener {

    private LightModel lightModel;
    private GameFrame gameFrame;

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

            gameFrame = new GameFrame();
            setButtonListener();
        });
    }

    @Override
    public void visit(DataBoard dataBoard) {
        SwingUtilities.invokeLater(() -> {
            gameFrame.setVisible(true);
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

                gameFrame.setButton(dataCell.getOldCell());
            }

            int newx = dataCell.getNewCell().getCoord().getX();
            int newy = dataCell.getNewCell().getCoord().getY();
            lightModel.getLightGrid()[newx][newy] = dataCell.getNewCell();

            gameFrame.setButton(dataCell.getNewCell());
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
            God chosenGod = gameFrame.selectGod(yourGod.getGods());

            ChoosenGod choosenGod = new ChoosenGod(chosenGod);
            notifyObservers(choosenGod);
        });
    }

    @Override
    public void visit(CurrentPlayer currentPlayer) {
        SwingUtilities.invokeLater(() -> gameFrame.setText(currentPlayer.getName() + "'s turn"));
    }

    @Override
    public void visit(PossiblePlacement possiblePlacement) {
        SwingUtilities.invokeLater(() -> {
            nextEvent = Event.PLACE_PAWN;
            gameFrame.setText("Where do you want to place your worker?");

            gameFrame.enableButtons(possiblePlacement.getCoordList(), null);
        });
    }

    @Override
    public void visit(SelectGods selectGods) {
        SwingUtilities.invokeLater(() -> {
            List<God> chosenGods = gameFrame.selectGods(selectGods.getGods(), selectGods.getNumberOfGods());
            SelectedGods selectedGods = new SelectedGods(chosenGods);

            notifyObservers(selectedGods);
        });

    }

    @Override
    public void visit(YouLose youLose) {
        SwingUtilities.invokeLater(() -> gameFrame.showLose(youLose.getName()));
    }

    @Override
    public void visit(YouWin youWin) {
        SwingUtilities.invokeLater(() -> gameFrame.showWin());
    }

    @Override
    public void visit(SelectPawn selectPawn) {
        SwingUtilities.invokeLater(() -> {
            switch (selectPawn.getValue()) {
                default:
                    int i = gameFrame.selectWorker();
                    notifyObservers(new SelectedPawn(i));
                    break;

                case 1:
                    gameFrame.setText("You can move only the worker number 1");
                    notifyObservers(new SelectedPawn(1));
                    break;

                case 2:
                    gameFrame.setText("You can move only the worker number 2");
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

                gameFrame.setText("End of the turn");
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
                        j = gameFrame.selectMove();

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
                        j = gameFrame.selectBuild();

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
            gameFrame.setText("Where do you want to build your Block?");

            gameFrame.enableButtons(possibleBuild.getCoordList(), possibleBuild.getGodsList());
        });

    }

    @Override
    public void visit(PossibleMove possibleMove) {
        SwingUtilities.invokeLater(() -> {
            nextEvent = Event.MOVE_ACTION;
            gameFrame.setText("Where do you want to move your worker?");

            gameFrame.enableButtons(possibleMove.getCoordList(), possibleMove.getGodsList());
        });

    }

    @Override
    public void visit(PossibleExtraAction possibleExtraAction) {
        SwingUtilities.invokeLater(() -> {
            nextEvent = Event.EXTRA_ACTION;
            gameFrame.setText("Where do you want to use Your God Action?");

            gameFrame.enableButtons(possibleExtraAction.getCoordList(), null);
        });
    }


    @Override
    public void update(Coord coord) {
        SwingUtilities.invokeLater(() -> {
            gameFrame.disableButtons();
            switch (nextEvent) {
                case PLACE_PAWN:
                    notifyObservers(new PlacePawn(coord));
                    break;

                case BUILD_ACTION:
                    boolean b = false;
                    if(trigger) {
                        int i = gameFrame.selectRoof();

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
        for(CellButton[] buttonsRow : gameFrame.getButtons()) {
            for(CellButton button :buttonsRow) {
                button.addButtonListener(this);
            }
        }
    }
}
