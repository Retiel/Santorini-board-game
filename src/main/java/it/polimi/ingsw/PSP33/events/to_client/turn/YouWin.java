package it.polimi.ingsw.PSP33.events.to_client.turn;

import it.polimi.ingsw.PSP33.events.to_client.MVEvent;
import it.polimi.ingsw.PSP33.events.to_client.MVEventVisitor;

/**
 * Win event
 */
public class YouWin implements MVEvent {

    /**
     * Winner's name
     */
    private final String name;

    public YouWin(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public void accept(MVEventVisitor MVEventVisitor) {
        MVEventVisitor.visit(this);
    }
}
