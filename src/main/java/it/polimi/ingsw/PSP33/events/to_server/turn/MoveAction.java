package it.polimi.ingsw.PSP33.events.to_server.turn;

import it.polimi.ingsw.PSP33.events.to_server.VCEvent;
import it.polimi.ingsw.PSP33.events.to_server.VCEventVisitor;
import it.polimi.ingsw.PSP33.utils.Coord;

/**
 * Move action event
 */
public class MoveAction implements VCEvent {

    /**
     * Move selected coordinates
     */
    private final Coord coord;

    public MoveAction(Coord coord) {
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
