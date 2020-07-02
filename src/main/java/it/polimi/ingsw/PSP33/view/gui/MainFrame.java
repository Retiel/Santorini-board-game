package it.polimi.ingsw.PSP33.view.gui;

import it.polimi.ingsw.PSP33.model.God;
import it.polimi.ingsw.PSP33.model.light_version.LightCell;
import it.polimi.ingsw.PSP33.utils.Coord;
import it.polimi.ingsw.PSP33.view.gui.components.*;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Frame used for the game
 */
public class MainFrame extends JFrame {

    /**
     * Panel used for the board's grid
     */
    private GridPanel gridPanel;

    /**
     * Panel used to show the client some text
     */
    private TextPanel rightTextPanel;

    /**
     * Panel used to show the client some text
     */
    private TextPanel leftTextPanel;

    /**
     * Panel used to show the client some text
     */
    private GodButton infoButton;

    public MainFrame() {
        //Main frame
        super("Santorini");
        setSize(1100, 668);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridBagLayout());
        setAlwaysOnTop(true);

        //Text panel
        rightTextPanel = new TextPanel("Welcome to Santorini", 150, 90, 16);
        leftTextPanel = new TextPanel("text", 200, 160, 16);

        //Housing panel of the grid
        BorderPanel borderPanel = new BorderPanel();
        gridPanel = new GridPanel();

        infoButton = new GodButton();
        infoButton.setSize(500, 300);

        /* Board panel components */
        //right board part
        ImagePanel rx = new ImagePanel("/santorini_right.png");
        rx.setLayout(new GridBagLayout());

        //left board part
        ImagePanel lx = new ImagePanel("/santorini_left.png");
        lx.setLayout(new GridBagLayout());

        //top board part
        ImagePanel top = new ImagePanel("/santorini_top.png");
        top.setLayout(new GridBagLayout());

        //bot board part
        ImagePanel bot = new ImagePanel("/santorini_bottom.png");
        bot.setLayout(new GridBagLayout());

        //center board part
        ImagePanel mid = new ImagePanel("/santorini_center.png");
        mid.setLayout(new GridBagLayout());

        // central component which houses rx, lx and center components
        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new GridBagLayout());

        /* List of constrains used */

        //text right constraint
        GridBagConstraints rightTextCon = new GridBagConstraints();
        rightTextCon.gridx = 0;
        rightTextCon.gridy = 0;
        rightTextCon.insets = new Insets(0,5,100,25);
        rightTextCon.anchor = GridBagConstraints.PAGE_START;
        rightTextCon.gridwidth = 2;
        rightTextCon.fill = GridBagConstraints.BOTH;

        //text left constraint
        GridBagConstraints leftTextCon = new GridBagConstraints();
        leftTextCon.gridx = 0;
        leftTextCon.gridy = 0;
        leftTextCon.insets = new Insets(150,20,200,25);
        leftTextCon.anchor = GridBagConstraints.CENTER;
        leftTextCon.gridwidth = 2;
        leftTextCon.fill = GridBagConstraints.BOTH;

        //top component constraint
        GridBagConstraints topPad = new GridBagConstraints();
        topPad.gridx = 0;
        topPad.gridy = 0;
        topPad.fill = GridBagConstraints.HORIZONTAL;

        //bot component constraint
        GridBagConstraints botPad = new GridBagConstraints();
        botPad.gridx = 0;
        botPad.gridy = 2;
        botPad.fill = GridBagConstraints.HORIZONTAL;

        //left component constraint
        GridBagConstraints lxPad = new GridBagConstraints();
        lxPad.gridx = 0;
        lxPad.gridy = 0;
        lxPad.fill = GridBagConstraints.VERTICAL;

        //center component constraint
        GridBagConstraints midPad = new GridBagConstraints();
        midPad.gridx = 1;
        midPad.gridy = 0;

        //right component constraint
        GridBagConstraints rxPad = new GridBagConstraints();
        rxPad.gridx = 2;
        rxPad.gridy = 0;
        rxPad.fill = GridBagConstraints.VERTICAL;
        borderPanel.add(gridPanel);
        mid.add(borderPanel);

        //central component constraint
        GridBagConstraints centralPanelCon = new GridBagConstraints();
        centralPanelCon.gridx = 0;
        centralPanelCon.gridy = 1;
        centralPanelCon.fill = GridBagConstraints.BOTH;

        //button constraint
        GridBagConstraints buttonCon = new GridBagConstraints();
        buttonCon.gridx = 0;
        buttonCon.gridy = 1;
        buttonCon.ipadx = 130;
        buttonCon.ipady = 25;
        buttonCon.insets = new Insets(0,0,20,30);
        buttonCon.anchor = GridBagConstraints.PAGE_END;

        // text added in the right panel with his constrains
        rx.add(rightTextPanel, rightTextCon);
        rx.add(infoButton, buttonCon);

        // text added in the left panel with his constrains
        lx.add(leftTextPanel, leftTextCon);

        // central row components added with their own constrains
        centralPanel.add(rx, rxPad);
        centralPanel.add(mid, midPad);
        centralPanel.add(lx, lxPad);

        // creating the game board with their own constrains on dimensions
        add(top, topPad);
        add(centralPanel, centralPanelCon);
        add(bot, botPad);

        pack();
        //setVisible(true);
    }

    /**
     * GridPanel getter
     * @return GridPanel
     */
    public GridPanel getGridPanel() {
        return gridPanel;
    }

    /**
     * Getter for the buttons of the grid
     * @return two dimensional array of buttons of the grid
     */
    public CellButton[][] getButtons() {
        return getGridPanel().getButtons();
    }

    /**
     * Getter for list of buttons using coordinates
     * @param coords list of coordinates
     * @return list of buttons relative to the list of coordinates
     */
    public List<CellButton> getButtonsByCoordinates(List<Coord> coords) {
        List<CellButton> buttons = new ArrayList<>();

        for(Coord coord : coords) {
            buttons.add(getButtons()[coord.getX()][coord.getY()]);
        }

        return buttons;
    }

    /**
     * Method used to set text to the right text panel
     * @param string text
     */
    public void setRightText(String string) {
        rightTextPanel.setLabelText(string);
    }

    /**
     * Method used to set text to the left text panel
     * @param string text
     */
    public void setLeftText(String string) {
        leftTextPanel.setLabelText(string);
    }

    /**
     * Method used to show a dialog to make the client select the gods used in the game
     * @param availableGods list of available gods
     * @param numberOfGods number of gods to select
     * @return list of selected gods
     */
    public List<God> selectGods(List<God> availableGods, int numberOfGods) {
        List<God> chosenGods = new ArrayList<>();

        String[] godNames;
        String[] options = {"OK"};

        for(int i = 0; i < numberOfGods; i++) {
            godNames = new String[availableGods.size()];
            for(God god : availableGods) {
                godNames[availableGods.indexOf(god)] = god.getName().name();
            }

            JComboBox<String> comboBox = new JComboBox<>(godNames);
            JOptionPane.showOptionDialog(this, comboBox, "Gods selection",
                    JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
            int k = comboBox.getSelectedIndex();
            chosenGods.add(availableGods.get(k));
            availableGods.remove(k);
        }

        return chosenGods;
    }

    /**
     * Method used to show a dialog to make the client select his god
     * @param availableGods list of available gods
     * @return selected god
     */
    public God selectGod(List<God> availableGods) {
        String[] godNames;
        String[] options = {"OK"};

        godNames = new String[availableGods.size()];
        for(God god : availableGods) {
            godNames[availableGods.indexOf(god)] = god.getName().name();
        }

        JComboBox<String> comboBox = new JComboBox<>(godNames);
        JOptionPane.showOptionDialog(this, comboBox, "Gods selection",
                JOptionPane.DEFAULT_OPTION, JOptionPane.PLAIN_MESSAGE, null, options, options[0]);
        int k = comboBox.getSelectedIndex();
        infoButton.setPlayerGod(availableGods.get(k));
        return availableGods.get(k);
    }

    /**
     * Method used to show a dialog to make the client select the worker he wants to use
     * @return selection (1. Worker1 2. Worker2)
     */
    public int selectWorker() {
        String[] options = {"Worker 1", "Worker 2"};

        int k = JOptionPane.showOptionDialog(this, "Which worker do you want to use?",
                "Worker selection", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        k++;
        return k;
    }

    /**
     * Method used to show a dialog to make the client select the type of move
     * @return selection (1. Move 2. God Effect)
     */
    public int selectMove() {
        String[] options = {"Move", "God Effect"};

        int k = JOptionPane.showOptionDialog(this, "What type of action do you want to do?",
                "Move selection", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        k++;
        return k;
    }


    /**
     * Method used to show a dialog to make the client select the type of build
     * @return selection (1. Build 2. God Effect)
     */
    public int selectBuild() {
        String[] options = {"Build", "God Effect"};

        int k = JOptionPane.showOptionDialog(this, "What type of action do you want to do?",
                "Build selection", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[1]);
        k++;
        return k;
    }

    /**
     * Method used to show a dialog to make the client select if he wants to build a roof
     * @return selection (0. Yes 1. No)
     */
    public int selectRoof() {
        return JOptionPane.showConfirmDialog(this, "Do you want to build a roof?",
                "Atlas", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }

    /**
     * Method used to show the lose dialog and then destroy the game frame
     * @param winner name of the winner
     */
    public void showLose(String winner) {
        JOptionPane.showMessageDialog(this, winner + " won!");
        setVisible(false);
        dispose();
    }

    /**
     * Method used to show the win dialog and then destroy the game frame
     */
    public void showWin() {
        JOptionPane.showMessageDialog(this, "You win!");
        setVisible(false);
        dispose();
    }

    /**
     * Method used to enable buttons for client's selection and set the border
     * @param coords list of coordinates of buttons to enable (default action)
     * @param gods list of coordinates of buttons to enable (god action)
     */
    public void enableButtons(List<Coord> coords, List<Coord> gods) {
        if(coords != null) {
            for (CellButton coordButton : getButtonsByCoordinates(coords)) {
                coordButton.setEnabled(true);
                coordButton.setColorBorder(Color.BLUE);
            }
        }

        if(gods != null) {
            for (CellButton godButton : getButtonsByCoordinates(gods)) {
                godButton.setEnabled(true);

                godButton.setColorBorder(Color.YELLOW);
            }
        }
    }

    /**
     * Method used to disable all buttons and set the border to default
     */
    public void disableButtons() {
        for(CellButton[] buttonsRow : getButtons()) {
            for(CellButton button :buttonsRow) {
                button.setEnabled(false);
                button.setDefaultBorder();
            }
        }
    }

    /**
     * Method used to set the new state of a cell button
     * @param cell light cell holding the new state to set
     */
    public void setButton(LightCell cell) {
        CellButton button = getButtons()[cell.getCoord().getX()][cell.getCoord().getY()];

        button.setFloor(cell.getFloor());
        button.setRoof(cell.isRoof());
        button.setPawn(cell.getOccupied());
    }
}
