package it.polimi.ingsw.PSP33.events.toServer.setup;

import it.polimi.ingsw.PSP33.events.VCEventVisitor;
import it.polimi.ingsw.PSP33.events.toServer.VCEvent;
import it.polimi.ingsw.PSP33.model.God;

import java.util.List;

public class SelectedGods implements VCEvent {

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
