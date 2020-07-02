package it.polimi.ingsw.PSP33.view.gui.components;

import it.polimi.ingsw.PSP33.model.light_version.LightPawn;
import it.polimi.ingsw.PSP33.utils.Coord;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

/**
 * JButton representing a single cell of the game's grid
 */
public class CellButton extends JButton implements ActionListener {

    /**
     * Label for floor information
     */
    private JLabel floorLabel;

    /**
     * Label for pawn information
     */
    private JLabel pawnLabel;

    /**
     * Label for roof information
     */
    private JLabel roofLabel;

    /**
     * Default JButton border
     */
    private Border defaultBorder;

    /**
     * Coordinates of the cell
     */
    private final Coord coord;

    /**
     * List of ButtonListener
     */
    private final List<ButtonListener> listeners = new ArrayList<>();

    public CellButton(Coord coord) {
        this.coord = coord;

        defaultBorder = getBorder();
        setLayout(new GridBagLayout());
        setContentAreaFilled(false);

        //Floor
        floorLabel = new JLabel();
        floorLabel.setFont(new Font("Verdana", Font.PLAIN,18));
        setFloor(0);
        GridBagConstraints floorCon = new GridBagConstraints();
        floorCon.gridx = 0;
        floorCon.gridy = 0;
        floorCon.weightx = 1;
        floorCon.weighty = 1;
        add(floorLabel, floorCon);

        //Roof
        roofLabel = new JLabel();
        roofLabel.setFont(new Font("Verdana", Font.PLAIN,18));

        GridBagConstraints roofCon = new GridBagConstraints();
        roofCon.gridx = 1;
        roofCon.gridy = 0;
        roofCon.weightx = 1;
        roofCon.weighty = 1;
        add(roofLabel, roofCon);

        //Pawn
        pawnLabel = new JLabel();
        pawnLabel.setFont(new Font("Verdana", Font.PLAIN,22));
        setPawn(null);
        GridBagConstraints pawnCon = new GridBagConstraints();
        pawnCon.gridx = 1;
        pawnCon.gridy = 1;
        pawnCon.weightx = 2;
        pawnCon.weighty = 2;
        add(pawnLabel, pawnCon);

        setEnabled(false);
        addActionListener(this);
    }


    @Override
    public void actionPerformed(ActionEvent e) {
        notifyButtonListener(coord);
    }

    /**
     * Method used to add a ButtonListener
     * @param listener ButtonListener
     */
    public void addButtonListener(ButtonListener listener) {
        synchronized (listeners) {
            listeners.add(listener);
        }
    }

    /**
     * Method used to notify ButtonListeners with coordinates
     * @param coord coordinates
     */
    public void notifyButtonListener(Coord coord) {
        synchronized (listeners) {
            for (ButtonListener listener : listeners) {
                listener.update(coord);
            }
        }
    }

    /**
     * Method used to set the floor label
     * @param floor number of floor
     */
    public void setFloor(int floor) {
        floorLabel.setText("" + floor);
    }

    /**
     * Method used to set the roof label
     * @param roof true if there is a roof
     */
    public void setRoof(boolean roof) {
        if(roof) {
            roofLabel.setText("R");
        } else {
            roofLabel.setText("");
        }
    }

    /**
     * Method used to set the pawn label
     * @param pawn LightPawn holding information of the pawn
     */
    public void setPawn(LightPawn pawn) {
        if(pawn != null) {
            pawnLabel.setText(pawn.getColor().name().substring(0, 1) + pawn.getNumber());
        } else {
            pawnLabel.setText("");
        }
    }

    /**
     * Method used to set a border with a custom color
     * @param color color of the border
     */
    public void setColorBorder(Color color) {
        setBorder(BorderFactory.createLineBorder(color, 2));
    }

    /**
     * Method used to set the default border
     */
    public void setDefaultBorder() {
        setBorder(defaultBorder);
    }
}
