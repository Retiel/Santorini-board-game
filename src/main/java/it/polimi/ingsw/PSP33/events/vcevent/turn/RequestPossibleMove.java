package it.polimi.ingsw.PSP33.events.vcevent.turn;

import it.polimi.ingsw.PSP33.events.VCEventVisitor;
import it.polimi.ingsw.PSP33.events.vcevent.VCEvent;

public class RequestPossibleMove extends VCEvent {

    private final int pawn;

    public RequestPossibleMove(int pawn) {
        this.pawn = pawn;
    }

    public int getPawn() {
        return pawn;
    }

    @Override
    public void accept(VCEventVisitor VCEventVisitor) {

    }
}
