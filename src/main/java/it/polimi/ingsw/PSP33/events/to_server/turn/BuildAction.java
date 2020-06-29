package it.polimi.ingsw.PSP33.events.to_server.turn;

import it.polimi.ingsw.PSP33.events.to_server.VCEventVisitor;
import it.polimi.ingsw.PSP33.events.to_server.VCEvent;
import it.polimi.ingsw.PSP33.utils.Coord;

public class BuildAction implements VCEvent {

    private final Coord coord;
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
