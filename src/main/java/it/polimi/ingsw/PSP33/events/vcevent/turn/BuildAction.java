package it.polimi.ingsw.PSP33.events.vcevent.turn;

import it.polimi.ingsw.PSP33.events.VCEventVisitor;
import it.polimi.ingsw.PSP33.events.vcevent.VCEvent;
import it.polimi.ingsw.PSP33.utils.Coord;

public class BuildAction extends VCEvent {

    private final Coord coord;

    public BuildAction(Coord coord) {
        this.coord = coord;
    }

    public Coord getCoord() {
        return coord;
    }

    @Override
    public void accept(VCEventVisitor VCEventVisitor) {

    }
}
