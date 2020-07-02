package it.polimi.ingsw.PSP33.view.gui.components;

import it.polimi.ingsw.PSP33.utils.Coord;

/**
 * Listener used by GUI to get notified by CellButtons
 */
public interface ButtonListener {

    /**
     * Update method of the oberver pattern
     * @param coord coordinates notified by CellButton
     */
    void update(Coord coord);
}
