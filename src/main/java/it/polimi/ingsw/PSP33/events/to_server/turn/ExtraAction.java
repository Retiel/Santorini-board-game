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

    private final boolean use;

    public ExtraAction(Coord coord, boolean use) {
        this.coord = coord;
        this.use = use;
    }

    public Coord getCoord() {
        return coord;
    }

    public boolean isUse() {
        return use;
    }

    @Override
    public void accept(VCEventVisitor VCEventVisitor) {
        VCEventVisitor.visit(this);
    }
}
