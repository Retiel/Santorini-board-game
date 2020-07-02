package it.polimi.ingsw.PSP33.events.to_server.setup;

import it.polimi.ingsw.PSP33.events.to_server.VCEvent;
import it.polimi.ingsw.PSP33.events.to_server.VCEventVisitor;
import it.polimi.ingsw.PSP33.model.God;

/**
 * Chosen god event
 */
public class ChoosenGod implements VCEvent {

    /**
     * Selected god
     */
    private final God god;

    public ChoosenGod(God god) {
        this.god = god;
    }

    public God getGod() {
        return god;
    }

    @Override
    public void accept(VCEventVisitor VCEventVisitor) {
        VCEventVisitor.visit(this);
    }
}
