package it.polimi.ingsw.PSP33.events.to_server.turn;

import it.polimi.ingsw.PSP33.events.to_server.VCEvent;
import it.polimi.ingsw.PSP33.events.to_server.VCEventVisitor;
import it.polimi.ingsw.PSP33.utils.Coord;

/**
 * Extra action event
 */
public class ExtraAction implements VCEvent {

    /**
     * Extra action selected coordinates
     */
    private final Coord coord;

    public ExtraAction(Coord coord) {
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
