package it.polimi.ingsw.PSP33.events.vcevent.setup;

import it.polimi.ingsw.PSP33.events.VCEventVisitor;
import it.polimi.ingsw.PSP33.events.vcevent.VCEvent;
import it.polimi.ingsw.PSP33.utils.Coord;

public class PlacePawn extends VCEvent {

    private Coord coord;

    public PlacePawn(Coord coord) {
        this.coord = coord;
    }

    public Coord getCoord() {
        return coord;
    }

    @Override
    public void accept(VCEventVisitor VCEventVisitor) {
        VCEventVisitor.visit(this);
    }
}
