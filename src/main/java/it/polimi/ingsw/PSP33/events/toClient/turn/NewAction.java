package it.polimi.ingsw.PSP33.events.toClient.turn;

import it.polimi.ingsw.PSP33.events.MVEventVisitor;
import it.polimi.ingsw.PSP33.events.toClient.MVEvent;


/**
 * First Message received by the client
 */
public class NewAction extends MVEvent {

    private final boolean move;
    private final boolean build;
    private final boolean extra;

    public NewAction(boolean move, boolean build, boolean extra) {
        this.move = move;
        this.build = build;
        this.extra = extra;
    }

    public boolean isMove() {
        return move;
    }

    public boolean isBuild() {
        return build;
    }

    public boolean isExtra() {
        return extra;
    }

    @Override
    public void accept(MVEventVisitor MVEventVisitor) {

    }
}
