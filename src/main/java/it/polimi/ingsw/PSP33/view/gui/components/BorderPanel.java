package it.polimi.ingsw.PSP33.view.gui.components;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * JPanel used to center the grid panel
 */
public class BorderPanel extends JPanel {

    public BorderPanel() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(new Insets(50, 65, 50, 50)));
        setOpaque(false);
    }
}
