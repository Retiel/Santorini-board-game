package it.polimi.ingsw.PSP33.view.gui.components;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class InfoButton extends JButton implements ActionListener {

    public InfoButton() {
        setLayout(new GridBagLayout());
        setContentAreaFilled(false);
        addActionListener(this);
        setEnabled(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {


    }

    private InputStream getImageInputStream(String path) {
        return getClass().getResourceAsStream(path);
    }
}
