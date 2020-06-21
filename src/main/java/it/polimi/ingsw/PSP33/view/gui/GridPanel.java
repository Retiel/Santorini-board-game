package it.polimi.ingsw.PSP33.view.gui;
import it.polimi.ingsw.PSP33.utils.Coord;

import javax.swing.*;
import java.awt.*;

public class GridPanel extends JPanel {

    private CellButton[][] buttons;
    private final static int DIM = 5;

    public GridPanel() {
        setLayout(new GridLayout(DIM,DIM));
        setPreferredSize(new Dimension(430, 450));

        buttons = new CellButton[DIM][DIM];

        for(int i = 0; i < DIM; i++) {
            for(int j = 0; j < DIM; j++) {
                buttons[i][j] = new CellButton(new Coord(i, j));
                add(buttons[i][j]);
            }
        }

        setOpaque(false);
    }

    public CellButton[][] getButtons() {
        return buttons;
    }
}
