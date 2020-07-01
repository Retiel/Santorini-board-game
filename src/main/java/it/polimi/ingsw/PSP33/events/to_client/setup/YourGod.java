package it.polimi.ingsw.PSP33.events.to_client.setup;

import it.polimi.ingsw.PSP33.events.to_client.MVEventVisitor;
import it.polimi.ingsw.PSP33.events.to_client.MVEvent;
import it.polimi.ingsw.PSP33.model.God;

import java.util.List;

/**
 * God selection event
 */
public class YourGod implements MVEvent {

    /**
     * List of available gods
     */
    private final List<God> gods;

    public YourGod(List<God> gods) {
        this.gods = gods;
    }

    public List<God> getGods() {
        return gods;
    }

    @Override
    public void accept(MVEventVisitor MVEventVisitor) {
        MVEventVisitor.visit(this);
    }
}
