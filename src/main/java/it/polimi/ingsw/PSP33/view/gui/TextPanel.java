package it.polimi.ingsw.PSP33.view.gui;

import javax.swing.*;
import java.awt.*;

public class TextPanel extends JPanel {

    private JTextArea textArea;

    public TextPanel() {
        setSize(600, 60);

        textArea = new JTextArea();
        textArea.setSize(570, 60);
        textArea.setEditable(false);
        textArea.setCursor(null);
        textArea.setOpaque(false);
        textArea.setFocusable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Verdana", Font.PLAIN, 14));
        textArea.setText("Welcome to Santorini");

        setOpaque(false);
        add(textArea);
    }

    public void setLabelText(String string) {
        textArea.setText(string);
    }
}
