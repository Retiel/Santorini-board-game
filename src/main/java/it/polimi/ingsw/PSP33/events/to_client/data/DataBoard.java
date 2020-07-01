package it.polimi.ingsw.PSP33.events.to_client.data;

import it.polimi.ingsw.PSP33.events.to_client.MVEventVisitor;
import it.polimi.ingsw.PSP33.events.to_client.MVEvent;
import it.polimi.ingsw.PSP33.model.light_version.LightBoard;

/**
 * Data event holding board data
 */
public class DataBoard implements MVEvent {

    /**
     * Light board
     */
    private final LightBoard lightBoard;

    public DataBoard(LightBoard lightBoard) {
        this.lightBoard = lightBoard;
    }

    public LightBoard getGrid() {
        return lightBoard;
    }

    @Override
    public void accept(MVEventVisitor MVEventVisitor) {
        MVEventVisitor.visit(this);
    }
}
