package it.polimi.ingsw.PSP33.events.toServer.setup;

import it.polimi.ingsw.PSP33.events.VCEventVisitor;
import it.polimi.ingsw.PSP33.events.toServer.VCEvent;
import it.polimi.ingsw.PSP33.model.God;

public class ChoosenGod implements VCEvent {

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
