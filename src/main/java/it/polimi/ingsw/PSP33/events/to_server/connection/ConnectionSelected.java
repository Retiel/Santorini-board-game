package it.polimi.ingsw.PSP33.events.to_server.connection;

import it.polimi.ingsw.PSP33.events.to_server.SCEvent;
import it.polimi.ingsw.PSP33.events.to_server.SCEventVisitor;

/**
 * Connection selected event
 */
public class ConnectionSelected implements SCEvent {

    /**
     * Connection selection
     */
    private final int selection;

    public ConnectionSelected(int selection) {
        this.selection = selection;
    }

    public int getSelection() {
        return selection;
    }

    @Override
    public void accept(SCEventVisitor scEventVisitor) {
        scEventVisitor.visit(this);
    }
}
