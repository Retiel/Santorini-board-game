package it.polimi.ingsw.PSP33.events.to_client.turn;

import it.polimi.ingsw.PSP33.events.to_client.MVEventVisitor;
import it.polimi.ingsw.PSP33.events.to_client.MVEvent;


/**
 * First Message received by the client
 */
public class NewAction implements MVEvent {

    private final boolean move;
    private final boolean build;
    private final boolean extra;

    private final boolean endTurn;

    public NewAction(boolean move, boolean build, boolean extra, boolean endTurn) {
        this.move = move;
        this.build = build;
        this.extra = extra;
        this.endTurn = endTurn;
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

    public boolean isEndTurn() {
        return endTurn;
    }

    @Override
    public void accept(MVEventVisitor MVEventVisitor) {
        MVEventVisitor.visit(this);
    }
}
