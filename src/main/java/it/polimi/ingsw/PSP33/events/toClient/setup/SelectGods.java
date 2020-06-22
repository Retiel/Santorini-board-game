package it.polimi.ingsw.PSP33.events.toClient.setup;

import it.polimi.ingsw.PSP33.events.MVEventVisitor;
import it.polimi.ingsw.PSP33.events.toClient.MVEvent;
import it.polimi.ingsw.PSP33.model.God;

import java.util.List;

public class SelectGods implements MVEvent {

    private final List<God> gods;
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
