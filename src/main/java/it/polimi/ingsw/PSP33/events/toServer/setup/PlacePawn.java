package it.polimi.ingsw.PSP33.events.toServer.setup;

import it.polimi.ingsw.PSP33.events.VCEventVisitor;
import it.polimi.ingsw.PSP33.events.toServer.VCEvent;
import it.polimi.ingsw.PSP33.utils.Coord;

public class PlacePawn implements VCEvent {

    private final Coord coord;

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
