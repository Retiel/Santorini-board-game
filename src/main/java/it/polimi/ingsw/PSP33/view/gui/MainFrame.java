package it.polimi.ingsw.PSP33.view.gui;

import javax.swing.*;

public class MainFrame extends JFrame {

    private ImagePanel imagePanel;
    private BorderPanel borderPanel;
    private GridPanel gridPanel;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(MainFrame::new);
    }

    public MainFrame() {
        super("Santorini");
        setSize(600, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        imagePanel = new ImagePanel("src/main/resources/SantoriniBoardResized.png");
        borderPanel = new BorderPanel();
        gridPanel = new GridPanel();

        imagePanel.add(borderPanel);
        borderPanel.add(gridPanel);
        add(imagePanel);

        pack();
        setVisible(true);
    }

    public ImagePanel getImagePanel() {
        return imagePanel;
    }

    public BorderPanel getBorderPanel() {
        return borderPanel;
    }

    public GridPanel getGridPanel() {
        return gridPanel;
    }

    public JButton[][] getButtons() {
        return getGridPanel().getButtons();
    }
}
