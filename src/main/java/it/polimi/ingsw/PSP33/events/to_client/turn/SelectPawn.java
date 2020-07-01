package it.polimi.ingsw.PSP33.events.to_client.turn;

import it.polimi.ingsw.PSP33.events.to_client.MVEventVisitor;
import it.polimi.ingsw.PSP33.events.to_client.MVEvent;

/**
 * Pawn selection event
 */
public class SelectPawn implements MVEvent {

    /**
     * Available pawns (0 == both pawns available)
     */
    private final int value;

    public SelectPawn(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    @Override
    public void accept(MVEventVisitor MVEventVisitor) {
        MVEventVisitor.visit(this);
    }
}
