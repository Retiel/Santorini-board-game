package it.polimi.ingsw.PSP33.events.toClient.setup;

import it.polimi.ingsw.PSP33.events.MVEventVisitor;
import it.polimi.ingsw.PSP33.events.toClient.MVEvent;

public class SetUpPawn extends MVEvent {

    private final int pawn;

    public SetUpPawn(int pawn) {
        this.pawn = pawn;
    }

    public int getPawn() {
        return pawn;
    }

    @Override
    public void accept(MVEventVisitor MVEventVisitor) {

    }
}
