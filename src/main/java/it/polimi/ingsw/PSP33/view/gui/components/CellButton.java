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

public class CellButton extends JButton implements ActionListener {

    private JLabel floorLabel;
    private JLabel pawnLabel;
    private JLabel roofLabel;

    private Border defaultBorder;

    private final Coord coord;

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

    public void addButtonListener(ButtonListener listener) {
        synchronized (listeners) {
            listeners.add(listener);
        }
    }

    public void notifyButtonListener(Coord coord) {
        synchronized (listeners) {
            for (ButtonListener listener : listeners) {
                listener.update(coord);
            }
        }
    }

    public void setFloor(int floor) {
        floorLabel.setText("" + floor);
    }

    public void setRoof(boolean roof) {
        if(roof) {
            roofLabel.setText("R");
        } else {
            roofLabel.setText("");
        }
    }

    public void setPawn(LightPawn pawn) {
        if(pawn != null) {
            pawnLabel.setText(pawn.getColor().name().substring(0, 1) + pawn.getNumber());
        } else {
            pawnLabel.setText("");
        }
    }

    public void setColorBorder(Color color) {
        setBorder(BorderFactory.createLineBorder(color, 2));
    }

    public void setDefaultBorder() {
        setBorder(defaultBorder);
    }
}
