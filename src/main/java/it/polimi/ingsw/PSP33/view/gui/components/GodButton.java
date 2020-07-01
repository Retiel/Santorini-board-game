package it.polimi.ingsw.PSP33.view.gui.components;

import it.polimi.ingsw.PSP33.model.God;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.InputStream;

public class GodButton extends JButton implements ActionListener {

    private God playerGod;

    public GodButton() {
        setContentAreaFilled(false);
        addActionListener(this);
        setEnabled(false);
        setVisible(false);
    }

    public void setPlayerGod(God playerGod) {
        this.playerGod = playerGod;
        setEnabled(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {

            TextPanel description = new TextPanel(playerGod.getDescription(),250, 90, 14);
            description.setBackground(null);

            ImagePanel panel1 = new ImagePanel(getImageInputStream("/info.png"));
            panel1.setLayout(new GridBagLayout());

            ImagePanel athena = new ImagePanel(getImageInputStream("/godCards/zeus.png"));

            GridBagConstraints c = new GridBagConstraints();
            c.gridx = 0;
            c.gridy = 0;
            c.insets = new Insets(0,0,69,0);
            c.anchor = GridBagConstraints.PAGE_START;
            panel1.add(athena, c);

            c.gridx = 0;
            c.gridy = 1;
            c.insets = new Insets(30,0,40,0);
            panel1.add(description, c);

            //Add the components.
            JDialog dialog = new JDialog();
            dialog.setLayout(new GridBagLayout());
            dialog.setSize(410, 635);
            dialog.setResizable(false);
            dialog.setAlwaysOnTop(true);

            dialog.add(panel1);

            dialog.setVisible(true);

        } catch (IOException ioException) {
            ioException.printStackTrace();
        }
    }

    private InputStream getImageInputStream(String path) {
        return getClass().getResourceAsStream(path);
    }

}
