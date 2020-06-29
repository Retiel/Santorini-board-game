package it.polimi.ingsw.PSP33.events.to_server.setup;

import it.polimi.ingsw.PSP33.events.to_server.VCEventVisitor;
import it.polimi.ingsw.PSP33.events.to_server.VCEvent;

public class PlayerDisconnected implements VCEvent {

    private final String name;

    public PlayerDisconnected(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(VCEventVisitor VCEventVisitor) {
        VCEventVisitor.visit(this);
    }
}
