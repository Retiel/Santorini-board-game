package it.polimi.ingsw.PSP33.events.to_client.turn;

import it.polimi.ingsw.PSP33.events.to_client.MVEventVisitor;
import it.polimi.ingsw.PSP33.events.to_client.MVEvent;


/**
 * New action event
 */
public class NewAction implements MVEvent {

    /**
     * Boolean for possible move
     */
    private final boolean move;

    /**
     * Boolean for possible build
     */
    private final boolean build;

    /**
     * Boolean for possible extra action
     */
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
        MVEventVisitor.visit(this);
    }
}
