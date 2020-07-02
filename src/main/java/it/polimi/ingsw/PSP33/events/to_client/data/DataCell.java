package it.polimi.ingsw.PSP33.events.to_client.data;

import it.polimi.ingsw.PSP33.events.to_client.MVEvent;
import it.polimi.ingsw.PSP33.events.to_client.MVEventVisitor;
import it.polimi.ingsw.PSP33.model.light_version.LightCell;

/**
 * Data event holding cell data
 */
public class DataCell implements MVEvent {

    /**
     * New cell lightcell
     */
    private final LightCell newCell;

    /**
     * Old cell lightcell
     */
    private final LightCell oldCell;

    public DataCell(LightCell newCell, LightCell oldCell) {
        this.newCell = newCell;
        this.oldCell = oldCell;
    }

    public LightCell getNewCell() {
        return newCell;
    }

    public LightCell getOldCell() {
        return oldCell;
    }

    @Override
    public void accept(MVEventVisitor MVEventVisitor) {
        MVEventVisitor.visit(this);
    }
}
