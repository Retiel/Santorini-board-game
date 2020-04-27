package it.polimi.ingsw.PSP33.events.toServer.turn;

import it.polimi.ingsw.PSP33.events.VCEventVisitor;
import it.polimi.ingsw.PSP33.events.toServer.VCEvent;

public class RequestPossibleBuild extends VCEvent {

    private final int pawn;

    public RequestPossibleBuild(int pawn) {
        this.pawn = pawn;
    }

    public int getPawn() {
        return pawn;
    }

    @Override
    public void accept(VCEventVisitor VCEventVisitor) {
        VCEventVisitor.visit(this);
    }
}
