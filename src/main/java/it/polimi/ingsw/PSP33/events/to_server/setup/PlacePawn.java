package it.polimi.ingsw.PSP33.events.to_server.setup;

import it.polimi.ingsw.PSP33.events.to_server.VCEventVisitor;
import it.polimi.ingsw.PSP33.events.to_server.VCEvent;
import it.polimi.ingsw.PSP33.utils.Coord;

/**
 * Placed pawn event
 */
public class PlacePawn implements VCEvent {

    /**
     * Selected pawn placement coordinates
     */
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
