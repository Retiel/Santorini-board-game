package it.polimi.ingsw.PSP33.view.gui.components;

import javax.swing.*;
import java.awt.*;

/**
 * JPanel used to show text with formatting
 */
public class TextPanel extends JPanel {

    /**
     * Text area used to show text
     */
    private JTextArea textArea;

    public TextPanel(String text, int width, int height, int size) {
        setSize(width, height);

        textArea = new JTextArea();
        textArea.setSize(width, height);
        textArea.setEditable(false);
        textArea.setCursor(null);
        textArea.setOpaque(false);
        textArea.setFocusable(false);
        textArea.setLineWrap(true);
        textArea.setWrapStyleWord(true);
        textArea.setFont(new Font("Verdana", Font.PLAIN, size));
        textArea.setText(text);

        setOpaque(false);
        add(textArea);
    }

    /**
     * Method used to set test in the text area
     * @param string text
     */
    public void setLabelText(String string) {
        textArea.setText(string);
    }
}
