package it.polimi.ingsw.PSP33.events.to_server.connection;

import it.polimi.ingsw.PSP33.events.to_server.SCEvent;
import it.polimi.ingsw.PSP33.events.to_server.SCEventVisitor;

/**
 * Number of players selected event
 */
public class NumberOfPlayersSelected implements SCEvent {

    /**
     * Number of players selection
     */
    private final int selection;

    public NumberOfPlayersSelected(int selection) {
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
