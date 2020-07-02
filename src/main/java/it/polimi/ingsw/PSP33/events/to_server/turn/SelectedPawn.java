package it.polimi.ingsw.PSP33.events.to_server.turn;

import it.polimi.ingsw.PSP33.events.to_server.VCEvent;
import it.polimi.ingsw.PSP33.events.to_server.VCEventVisitor;

/**
 * Pawn selected event
 */
public class SelectedPawn implements VCEvent {

    /**
     * Pawn selected number
     */
    private final int pawn;

    public SelectedPawn(int pawn) {
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
