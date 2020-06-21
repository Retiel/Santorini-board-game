package it.polimi.ingsw.PSP33.view.gui;

import it.polimi.ingsw.PSP33.events.toClient.data.DataBoard;
import it.polimi.ingsw.PSP33.events.toClient.data.DataCell;
import it.polimi.ingsw.PSP33.events.toClient.data.DataPlayer;
import it.polimi.ingsw.PSP33.events.toClient.setup.CurrentPlayer;
import it.polimi.ingsw.PSP33.events.toClient.setup.PossiblePlacement;
import it.polimi.ingsw.PSP33.events.toClient.setup.SelectGods;
import it.polimi.ingsw.PSP33.events.toClient.setup.YourGod;
import it.polimi.ingsw.PSP33.events.toClient.turn.*;
import it.polimi.ingsw.PSP33.events.toServer.setup.ChoosenGod;
import it.polimi.ingsw.PSP33.events.toServer.setup.PlacePawn;
import it.polimi.ingsw.PSP33.events.toServer.setup.SelectedGods;
import it.polimi.ingsw.PSP33.events.toServer.turn.*;
import it.polimi.ingsw.PSP33.model.God;
import it.polimi.ingsw.PSP33.model.light_version.LightModel;
import it.polimi.ingsw.PSP33.utils.Coord;
import it.polimi.ingsw.PSP33.view.AbstractView;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
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
    private Event next;
    private boolean trigger;


    public GUI() {
        lightModel = new LightModel();

        SwingUtilities.invokeLater(() -> {
            mainFrame = new MainFrame();
            setButtonListener();
        });
    }

    @Override
    public void visit(DataBoard dataBoard) {
        mainFrame.setVisible(true);
        lightModel.setLightGrid(dataBoard.getGrid().getGrid());
    }

    @Override
    public void visit(DataCell dataCell) {
        if (dataCell.getOldCell() != null){
            int oldx = dataCell.getOldCell().getCoord().getX();
            int oldy = dataCell.getOldCell().getCoord().getY();
            lightModel.getLightGrid()[oldx][oldy] = dataCell.getOldCell();

            mainFrame.getButtons()[oldx][oldy].setFloor(dataCell.getOldCell().getFloor());
            mainFrame.getButtons()[oldx][oldy].setPawn(dataCell.getOldCell().getOccupied());
        }

        int newx = dataCell.getNewCell().getCoord().getX();
        int newy = dataCell.getNewCell().getCoord().getY();
        lightModel.getLightGrid()[newx][newy] = dataCell.getNewCell();

        mainFrame.getButtons()[newx][newy].setFloor(dataCell.getNewCell().getFloor());
        mainFrame.getButtons()[newx][newy].setPawn(dataCell.getNewCell().getOccupied());
    }

    @Override
    public void visit(DataPlayer dataPlayer) {
        lightModel.setPlayers(dataPlayer.getPlayer());
        lightModel.setPlayerName(dataPlayer.getName());
    }

    @Override
    public void visit(YourGod yourGod) {
        List<God> gods = yourGod.getGods();


        SwingUtilities.invokeLater(() -> {
            String[] godNames = new String[gods.size()];
            String[] options = {"OK"};

            for(God god : gods) {
                godNames[gods.indexOf(god)] = god.getName().name();
            }

            JComboBox<String> comboBox = new JComboBox<>(godNames);
            JOptionPane.showOptionDialog(null, comboBox, "Gods selection",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            int k = comboBox.getSelectedIndex();

            ChoosenGod choosenGod = new ChoosenGod(gods.get(k));
            notifyObservers(choosenGod);
        });
    }

    @Override
    public void visit(CurrentPlayer currentPlayer) {
        SwingUtilities.invokeLater(() -> mainFrame.setText(currentPlayer.getName() + "'s turn"));
    }

    @Override
    public void visit(PossiblePlacement possiblePlacement) {
        next = Event.PLACE_PAWN;

        SwingUtilities.invokeLater(() -> {
            mainFrame.setText("Where do you want to place your worker?");

            enableButtons(possiblePlacement.getCoordList(), null);
        });
    }

    @Override
    public void visit(SelectGods selectGods) {
        List<God> chosenGods = new ArrayList<>();
        List<God> gods = selectGods.getGods();


        SwingUtilities.invokeLater(() -> {
            String[] godNames;
            String[] options = {"OK"};

            for(int i = 0; i < lightModel.getPlayers().size(); i++) {
                godNames = new String[gods.size()];
                for(God god : gods) {
                    godNames[gods.indexOf(god)] = god.getName().name();
                }

                JComboBox<String> comboBox = new JComboBox<>(godNames);
                JOptionPane.showOptionDialog(null, comboBox, "Gods selection",
                        JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
                int k = comboBox.getSelectedIndex();
                chosenGods.add(gods.get(k));
                gods.remove(k);
            }

            SelectedGods selectedGods = new SelectedGods(chosenGods);
            notifyObservers(selectedGods);
        });

    }

    @Override
    public void visit(YouLose youLose) {
        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null, "" + youLose.getName() + "won!"));
    }

    @Override
    public void visit(YouWin youWin) {
        SwingUtilities.invokeLater(() -> JOptionPane.showMessageDialog(null, "You win!"));
    }

    @Override
    public void visit(SelectPawn selectPawn) {

        SwingUtilities.invokeLater(() -> {
            switch (selectPawn.getValue()) {
                default:
                    String[] options = {"Worker 1", "Worker 2"};

                    int i = JOptionPane.showOptionDialog(null, "Which worker do you want to use?",
                            "Worker selection", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                            null, options, options[1]);
                    notifyObservers(new SelectedPawn(i + 1));
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

                mainFrame.setText("End of the turn:");
                lightModel.setYourTurn(false);
            } else{

                lightModel.setYourTurn(true);
                //decide action with the Boolean and send input to controller (switch case)
                if (newAction.isMove()){
                    if (!newAction.isExtra()){
                        //send move action to controller
                        rpm = new RequestPossibleMove();
                        notifyObservers(rpm);
                    }
                    else {
                        //choose and create possible move or extra message to notify to the controller
                        mainFrame.setText("\n1) Move\n2) God Effect");
                        String[] options = {"Move", "God Effect"};

                        j = JOptionPane.showOptionDialog(null, "What type of action do you want to do?",
                                "Effect selection", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                                null, options, options[1]);
                        j++;
                        if (j==1){
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
                        rpb = new RequestPossibleBuild();
                        notifyObservers(rpb);
                    }
                    else{
                        //choose your action and send proper message to server
                        String[] options = {"Build", "God Effect"};
                        j = JOptionPane.showOptionDialog(null, "What type of action do you want to do?",
                                "Effect selection", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                                null, options, options[1]);
                        j++;
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
        next = Event.BUILD_ACTION;
        trigger = possibleBuild.isRoofAvailable();

        SwingUtilities.invokeLater(() -> {
            mainFrame.setText("Where do you want to build your Block?");

            enableButtons(possibleBuild.getCoordList(), possibleBuild.getGodsList());
        });

    }

    @Override
    public void visit(PossibleMove possibleMove) {
        next = Event.MOVE_ACTION;

        SwingUtilities.invokeLater(() -> {
            mainFrame.setText("Where do you want to move your worker?");

            enableButtons(possibleMove.getCoordList(), possibleMove.getGodsList());
        });

    }

    @Override
    public void visit(PossibleExtraAction possibleExtraAction) {
        next = Event.EXTRA_ACTION;

        SwingUtilities.invokeLater(() -> {
            mainFrame.setText("Where do you want to use Your God Action?");

            enableButtons(possibleExtraAction.getCoordList(), null);
        });

    }


    @Override
    public void update(Coord coord) {
        disableButtons();
        switch (next) {
            case PLACE_PAWN:
                notifyObservers(new PlacePawn(coord));
                break;

            case BUILD_ACTION:
                boolean b = false;
                if(trigger) {
                    int i = JOptionPane.showConfirmDialog(null, "Do you want to build a roof?",
                            "Atlas", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);

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
    }

    public void enableButtons(List<Coord> coords, List<Coord> gods) {
        SwingUtilities.invokeLater(() -> {
            if(coords != null) {
                for (CellButton coordButton : mainFrame.getButtonsByCoordinates(coords)) {
                    coordButton.setEnabled(true);
                    coordButton.setBorder(BorderFactory.createLineBorder(Color.GREEN, 2));
                }
            }

            if(gods != null) {
                for (CellButton godButton : mainFrame.getButtonsByCoordinates(gods)) {
                    godButton.setEnabled(true);
                    godButton.setBorder(BorderFactory.createLineBorder(Color.YELLOW, 2));
                }
            }
        });
    }

    public void disableButtons() {
        SwingUtilities.invokeLater(() -> {
            for(CellButton[] buttonsRow : mainFrame.getButtons()) {
                for(CellButton button :buttonsRow) {
                    button.setEnabled(false);
                    button.setBorder(BorderFactory.createLineBorder(Color.BLACK, 0));
                }
            }
        });

    }

    public void setButtonListener() {
        for(CellButton[] buttonsRow : mainFrame.getButtons()) {
            for(CellButton button :buttonsRow) {
                button.addButtonListener(this);
            }
        }
    }
}
