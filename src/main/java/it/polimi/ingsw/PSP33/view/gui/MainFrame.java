package it.polimi.ingsw.PSP33.view.gui;

import it.polimi.ingsw.PSP33.model.God;
import it.polimi.ingsw.PSP33.model.light_version.LightCell;
import it.polimi.ingsw.PSP33.utils.Coord;
import it.polimi.ingsw.PSP33.view.gui.components.*;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
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
    private TextPanel textPanel;

    public MainFrame() throws IOException {
        //Main frame
        super("Santorini");
        setSize(1100, 668);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridBagLayout());
        setAlwaysOnTop(true);


        //Text panel
        textPanel = new TextPanel();

        //Housing panel of the grid
        BorderPanel borderPanel = new BorderPanel();
        gridPanel = new GridPanel();

        /* Board panel components */
        //right board part
        ImagePanel rx = new ImagePanel(getImageInputStream("/santorini_right.png"));
        rx.setLayout(new GridBagLayout());

        //left board part
        ImagePanel lx = new ImagePanel(getImageInputStream("/santorini_left.png"));
        lx.setLayout(new GridBagLayout());

        //top board part
        ImagePanel top = new ImagePanel(getImageInputStream("/santorini_top.png"));
        top.setLayout(new GridBagLayout());

        //bot board part
        ImagePanel bot = new ImagePanel(getImageInputStream("/santorini_bottom.png"));
        bot.setLayout(new GridBagLayout());

        //center board part
        ImagePanel mid = new ImagePanel(getImageInputStream("/santorini_center.png"));
        mid.setLayout(new GridBagLayout());

        // central component which houses rx, lx and center components
        JPanel centralPanel = new JPanel();
        centralPanel.setLayout(new GridBagLayout());

        /* List of constrains used */

        //text constrain
        GridBagConstraints textCon = new GridBagConstraints();
        textCon.gridx = 0;
        textCon.gridy = 0;
        textCon.insets = new Insets(0,5,225,25);
        textCon.anchor = GridBagConstraints.PAGE_START;
        textCon.gridwidth = 2;
        textCon.fill = GridBagConstraints.BOTH;

        //top component constrain
        GridBagConstraints topPad = new GridBagConstraints();
        topPad.gridx = 0;
        topPad.gridy = 0;
        topPad.fill = GridBagConstraints.HORIZONTAL;

        //bot component constrain
        GridBagConstraints botPad = new GridBagConstraints();
        botPad.gridx = 0;
        botPad.gridy = 2;
        botPad.fill = GridBagConstraints.HORIZONTAL;

        //left component constrain
        GridBagConstraints lxPad = new GridBagConstraints();
        lxPad.gridx = 0;
        lxPad.gridy = 0;
        lxPad.fill = GridBagConstraints.VERTICAL;

        //center component constrain
        GridBagConstraints midPad = new GridBagConstraints();
        midPad.gridx = 1;
        midPad.gridy = 0;

        //right component constrain
        GridBagConstraints rxPad = new GridBagConstraints();
        rxPad.gridx = 2;
        rxPad.gridy = 0;
        rxPad.fill = GridBagConstraints.VERTICAL;
        borderPanel.add(gridPanel);
        mid.add(borderPanel);

        //text constrain
        GridBagConstraints centralPanelCon = new GridBagConstraints();
        centralPanelCon.gridx = 0;
        centralPanelCon.gridy = 1;
        centralPanelCon.fill = GridBagConstraints.BOTH;

        // text added in the right panel with his constrains
        rx.add(textPanel, textCon);

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
     * Method used to set text to the text panel
     * @param string text
     */
    public void setText(String string) {
        textPanel.setLabelText(string);
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
        int k = JOptionPane.showConfirmDialog(this, "Do you want to build a roof?",
                "Atlas", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return k;
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

    /**
     * Method used to get input stream for an image
     * @param path path of the image in resources
     * @return input stream of the image
     */
    private InputStream getImageInputStream(String path) {
        return getClass().getResourceAsStream(path);
    }
}
