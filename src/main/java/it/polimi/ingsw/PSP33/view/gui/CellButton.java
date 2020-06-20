package it.polimi.ingsw.PSP33.view.gui;
import it.polimi.ingsw.PSP33.utils.Coord;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class CellButton extends JButton implements ActionListener {

    private JLabel floorLabel;
    private JLabel pawnLabel;

    private int floor;

    private Coord coord;

    public CellButton(Coord coord) {
        this.coord = coord;

        setLayout(new BorderLayout());
        setContentAreaFilled(false);

        floor = 0;
        floorLabel = new JLabel(""+floor);
        add(floorLabel, BorderLayout.PAGE_START);
        addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(floor == 3) {
            floor = -1;
        }else {
            if(floor != -1) {
                floor++;
            }
        }

        setFloorLabelText();
    }

    public void setFloorLabelText() {
        String string;

        if(floor == -1) {
            string = "R";
        } else {
            string = ""+floor;
        }

        floorLabel.setText(string);
    }
}
