package it.polimi.ingsw.PSP33.view.gui;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

public class BorderPanel extends JPanel {

    public BorderPanel() {
        setLayout(new BorderLayout());
        setBorder(new EmptyBorder(new Insets(70, 65, 50, 50)));
        setOpaque(false);
    }
}
