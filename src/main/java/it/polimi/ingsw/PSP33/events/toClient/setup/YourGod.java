package it.polimi.ingsw.PSP33.events.toClient.setup;

import it.polimi.ingsw.PSP33.events.MVEventVisitor;
import it.polimi.ingsw.PSP33.events.toClient.MVEvent;
import it.polimi.ingsw.PSP33.model.God;

import java.util.List;

public class YourGod implements MVEvent {

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
