package it.polimi.ingsw.PSP33.events.toClient.data;

import it.polimi.ingsw.PSP33.events.MVEventVisitor;
import it.polimi.ingsw.PSP33.events.toClient.MVEvent;
import it.polimi.ingsw.PSP33.model.light_version.LightPawn;

public class DataPawn implements MVEvent {

    private final LightPawn pawn;

    public DataPawn(LightPawn pawn) {
        this.pawn = pawn;
    }

    public LightPawn getPawn() {
        return pawn;
    }

    @Override
    public void accept(MVEventVisitor MVEventVisitor) {
        MVEventVisitor.visit(this);
    }
}
