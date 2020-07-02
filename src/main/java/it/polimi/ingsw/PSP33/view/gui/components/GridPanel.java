package it.polimi.ingsw.PSP33.view.gui.components;

import it.polimi.ingsw.PSP33.utils.Coord;

import javax.swing.*;
import java.awt.*;

/**
 * JPanel used to show the game's grid
 */
public class GridPanel extends JPanel {

    /**
     * Grid of CellButtons
     */
    private CellButton[][] buttons;

    /**
     * Size of the grid
     */
    private final static int DIM = 5;

    public GridPanel() {
        setLayout(new GridLayout(DIM,DIM));
        setPreferredSize(new Dimension(408, 425));

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
