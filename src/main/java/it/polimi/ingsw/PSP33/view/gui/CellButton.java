package it.polimi.ingsw.PSP33.view.gui;
import it.polimi.ingsw.PSP33.model.light_version.LightPawn;
import it.polimi.ingsw.PSP33.utils.Coord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CellButton extends JButton implements ActionListener {

    //TODO: roof label
    private JLabel floorLabel;
    private JLabel pawnLabel;

    private int floor;
    //private String pawn;
    private LightPawn pawn;

    private Coord coord;

    private final List<ButtonListener> listeners = new ArrayList<>();

    public CellButton(Coord coord) {
        this.coord = coord;

        setLayout(new GridBagLayout());
        setContentAreaFilled(false);

        floor = 0;
        floorLabel = new JLabel();
        floorLabel.setFont(new Font("Verdana", Font.PLAIN,18));
        floorLabel.setText("" + floor);
        GridBagConstraints floorCon = new GridBagConstraints();
        floorCon.gridx = 0;
        floorCon.gridy = 0;
        floorCon.weightx = 1;
        floorCon.weighty = 1;
        add(floorLabel, floorCon);

        pawn = null;
        pawnLabel = new JLabel();
        pawnLabel.setFont(new Font("Verdana", Font.PLAIN,22));
        pawnLabel.setText("");
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

    public void removeButtonListener(ButtonListener listener) {
        synchronized (listeners) {
            listeners.remove(listener);
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
        this.floor = floor;
        floorLabel.setText("" + floor);
    }

    public void setPawn(LightPawn pawn) {
        this.pawn = pawn;
        if(pawn != null) {
            pawnLabel.setText(pawn.getColor().name().substring(0, 1) + pawn.getNumber());
        } else {
            pawnLabel.setText("");
        }
    }
}
