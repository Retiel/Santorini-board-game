package it.polimi.ingsw.PSP33.view.gui;

import it.polimi.ingsw.PSP33.utils.Coord;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class MainFrame extends JFrame {

    private ImagePanel boardImagePanel;
    private BorderPanel borderPanel;
    private GridPanel gridPanel;

    private ImagePanel textImagePanel;
    private TextPanel textPanel;

    public MainFrame() {
        super("Santorini");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setLayout(new GridBagLayout());

        //Text panel
        textImagePanel = new ImagePanel("src/main/resources/SantoriniPanelMidResized.png");
        textPanel = new TextPanel();

        GridBagConstraints textCon = new GridBagConstraints();
        textCon.gridx = 1;
        textCon.gridy = 0;
        textImagePanel.add(textPanel);
        add(textImagePanel, textCon);

        //Board panel
        boardImagePanel = new ImagePanel("src/main/resources/SantoriniBoardResized.png");
        borderPanel = new BorderPanel();
        gridPanel = new GridPanel();

        boardImagePanel.add(borderPanel);
        borderPanel.add(gridPanel);
        GridBagConstraints imageCon = new GridBagConstraints();
        imageCon.gridx = 1;
        imageCon.gridy = 1;
        add(boardImagePanel, imageCon);


        pack();
        //setVisible(true);
    }

    public ImagePanel getBoardImagePanel() {
        return boardImagePanel;
    }

    public BorderPanel getBorderPanel() {
        return borderPanel;
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

    public ImagePanel getTextImagePanel() {
        return textImagePanel;
    }

    public TextPanel getTextPanel() {
        return textPanel;
    }

    public void setText(String string) {
        textPanel.setLabelText(string);
    }
}
