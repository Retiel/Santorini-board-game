package it.polimi.ingsw.PSP33.events.to_client.setup;

import it.polimi.ingsw.PSP33.events.to_client.MVEventVisitor;
import it.polimi.ingsw.PSP33.events.to_client.MVEvent;
import it.polimi.ingsw.PSP33.model.God;

import java.util.List;

/**
 * Gods selection event
 */
public class SelectGods implements MVEvent {

    /**
     * List of available gods
     */
    private final List<God> gods;

    /**
     * Number of gods to select
     */
    private final int numberOfGods;

    public SelectGods(List<God> gods, int numberOfGods) {
        this.gods = gods;
        this.numberOfGods = numberOfGods;
    }

    public List<God> getGods() {
        return gods;
    }

    public int getNumberOfGods() {
        return numberOfGods;
    }

    @Override
    public void accept(MVEventVisitor MVEventVisitor) {
        MVEventVisitor.visit(this);
    }
}
