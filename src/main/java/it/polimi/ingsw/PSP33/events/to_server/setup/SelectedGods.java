package it.polimi.ingsw.PSP33.events.to_server.setup;

import it.polimi.ingsw.PSP33.events.to_server.VCEventVisitor;
import it.polimi.ingsw.PSP33.events.to_server.VCEvent;
import it.polimi.ingsw.PSP33.model.God;

import java.util.List;

/**
 * Selected gods event
 */
public class SelectedGods implements VCEvent {

    /**
     * List of seleted gods for the game
     */
    private final List<God> gods;

    public SelectedGods(List<God> gods) {
        this.gods = gods;
    }

    public List<God> getGods() {
        return gods;
    }

    @Override
    public void accept(VCEventVisitor VCEventVisitor) {
        VCEventVisitor.visit(this);
    }
}
