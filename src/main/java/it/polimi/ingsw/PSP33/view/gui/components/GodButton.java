package it.polimi.ingsw.PSP33.view.gui.components;

import it.polimi.ingsw.PSP33.model.God;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * JButton used to get the god card informations
 */
public class GodButton extends JButton implements ActionListener {

    /**
     * Panel for card informations
     */
    private ImagePanel infoPanel;
    private JDialog dialog;

    public GodButton() {
        setContentAreaFilled(false);
        addActionListener(this);
        setEnabled(false);
        setVisible(false);
    }

    /**
     * Method used to set the god of the player to the button
     * @param playerGod god of the player
     */
    public void setPlayerGod(God playerGod) {

        infoPanel = new ImagePanel("/santorini_info.png");
        infoPanel.setLayout(new GridBagLayout());

        TextPanel descriptionPanel = new TextPanel(playerGod.getDescription(), 250, 90, 14);
        descriptionPanel.setBackground(null);

        ImagePanel cardPanel = new ImagePanel("/gods/" + playerGod.getName().name() + ".png");

        GridBagConstraints cardCon = new GridBagConstraints();
        cardCon.gridx = 0;
        cardCon.gridy = 0;
        cardCon.insets = new Insets(0, 0, 69, 0);
        cardCon.anchor = GridBagConstraints.PAGE_START;
        infoPanel.add(cardPanel, cardCon);

        GridBagConstraints descriptionCon = new GridBagConstraints();
        descriptionCon.gridx = 0;
        descriptionCon.gridy = 1;
        descriptionCon.insets = new Insets(30, 0, 40, 0);
        infoPanel.add(descriptionPanel, descriptionCon);

        setEnabled(true);
        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        //Show dialog
        if(dialog != null) dialog.dispose();
        dialog = new JDialog();
        dialog.setLayout(new GridBagLayout());
        dialog.setSize(410, 635);
        dialog.setResizable(false);
        dialog.setAlwaysOnTop(true);

        dialog.add(infoPanel);

        dialog.setVisible(true);
    }
}
