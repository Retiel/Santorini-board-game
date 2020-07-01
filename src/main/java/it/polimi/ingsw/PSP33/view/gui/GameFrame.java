package it.polimi.ingsw.PSP33.view.gui;

import it.polimi.ingsw.PSP33.model.God;
import it.polimi.ingsw.PSP33.model.light_version.LightCell;
import it.polimi.ingsw.PSP33.utils.Coord;
import it.polimi.ingsw.PSP33.view.gui.components.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

public class GameFrame extends JFrame {

    private GridPanel gridPanel;
    private TextPanel textPanel;

    public GameFrame() throws IOException {
        //Main frame
        super("Santorini");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridBagLayout());
        setAlwaysOnTop(true);

        InputStream imageStream;

        //Text panel
        imageStream = getClass().getResourceAsStream("/SantoriniPanelMidResized.png");
        ImagePanel textImagePanel = new ImagePanel(imageStream);
        textPanel = new TextPanel();

        GridBagConstraints textCon = new GridBagConstraints();
        textCon.gridx = 1;
        textCon.gridy = 0;
        textImagePanel.add(textPanel);
        add(textImagePanel, textCon);

        //Board panel
        imageStream = getClass().getResourceAsStream("/SantoriniBoardResized.png");
        ImagePanel boardImagePanel = new ImagePanel(imageStream);
        BorderPanel borderPanel = new BorderPanel();
        gridPanel = new GridPanel();

        boardImagePanel.add(borderPanel);
        borderPanel.add(gridPanel);
        GridBagConstraints imageCon = new GridBagConstraints();
        imageCon.gridx = 1;
        imageCon.gridy = 1;
        add(boardImagePanel, imageCon);

        pack();
    }

    public GridPanel getGridPanel() {
        return gridPanel;
    }

    public CellButton[][] getButtons() {
        return getGridPanel().getButtons();
    }

    public List<CellButton> getButtonsByCoordinates(List<Coord> coords) {
        List<CellButton> buttons = new ArrayList<>();

        for(Coord coord : coords) {
            buttons.add(getButtons()[coord.getX()][coord.getY()]);
        }

        return buttons;
    }

    public void setText(String string) {
        textPanel.setLabelText(string);
    }

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

    public int selectWorker() {
        String[] options = {"Worker 1", "Worker 2"};

        int k = JOptionPane.showOptionDialog(this, "Which worker do you want to use?",
                "Worker selection", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        k++;
        return k;
    }

    public int selectMove() {
        String[] options = {"Move", "God Effect"};

        int k = JOptionPane.showOptionDialog(this, "What type of action do you want to do?",
                "Move selection", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[0]);
        k++;
        return k;
    }

    public int selectBuild() {
        String[] options = {"Build", "God Effect"};

        int k = JOptionPane.showOptionDialog(this, "What type of action do you want to do?",
                "Build selection", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
                null, options, options[1]);
        k++;
        return k;
    }

    public int selectRoof() {
        int k = JOptionPane.showConfirmDialog(this, "Do you want to build a roof?",
                "Atlas", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        return k;
    }

    public void showLose(String winner) {
        JOptionPane.showMessageDialog(this, winner + " won!");
        setVisible(false);
        dispose();
    }

    public void showWin() {
        JOptionPane.showMessageDialog(this, "You win!");
        setVisible(false);
        dispose();
    }

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

    public void disableButtons() {
        for(CellButton[] buttonsRow : getButtons()) {
            for(CellButton button :buttonsRow) {
                button.setEnabled(false);
                button.setDefaultBorder();
            }
        }
    }

    public void setButton(LightCell cell) {
        CellButton button = getButtons()[cell.getCoord().getX()][cell.getCoord().getY()];

        button.setFloor(cell.getFloor());
        button.setRoof(cell.isRoof());
        button.setPawn(cell.getOccupied());
    }
}
