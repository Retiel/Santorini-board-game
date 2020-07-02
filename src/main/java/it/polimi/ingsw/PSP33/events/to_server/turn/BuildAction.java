package it.polimi.ingsw.PSP33.events.to_server.turn;

import it.polimi.ingsw.PSP33.events.to_server.VCEvent;
import it.polimi.ingsw.PSP33.events.to_server.VCEventVisitor;
import it.polimi.ingsw.PSP33.utils.Coord;

/**
 * Build action event
 */
public class BuildAction implements VCEvent {

    /**
     * Build coordinates selected
     */
    private final Coord coord;

    /**
     * Build roof selected
     */
    private final boolean roof;

    public BuildAction(Coord coord, boolean roof) {
        this.coord = coord;
        this.roof = roof;
    }

    public Coord getCoord() {
        return coord;
    }

    public boolean isRoof() {
        return roof;
    }

    @Override
    public void accept(VCEventVisitor VCEventVisitor) {
        VCEventVisitor.visit(this);
    }
}
