package it.polimi.ingsw.PSP33.events.to_client.setup;

import it.polimi.ingsw.PSP33.events.to_client.MVEvent;
import it.polimi.ingsw.PSP33.events.to_client.MVEventVisitor;

/**
 * Current player event
 */
public class CurrentPlayer implements MVEvent {

    /**
     * Name of the current player
     */
    private final String name;

    public CurrentPlayer(String name) {
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
