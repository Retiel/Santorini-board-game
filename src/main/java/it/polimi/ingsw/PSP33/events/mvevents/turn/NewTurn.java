package it.polimi.ingsw.PSP33.events.mvevents.turn;

import it.polimi.ingsw.PSP33.events.MVEventVisitor;
import it.polimi.ingsw.PSP33.events.mvevents.MVEvent;


/**
 * First Message received by the client
 */
public class NewTurn extends MVEvent {

    private final boolean move;
    private final boolean build;

    public NewTurn(boolean move, boolean build) {
        this.move = move;
        this.build = build;
    }

    public boolean isMove() {
        return move;
    }

    public boolean isBuild() {
        return build;
    }

    @Override
    public void accept(MVEventVisitor MVEventVisitor) {

    }
}
