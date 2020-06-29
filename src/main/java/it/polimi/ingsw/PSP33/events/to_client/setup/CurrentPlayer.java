package it.polimi.ingsw.PSP33.events.to_client.setup;

import it.polimi.ingsw.PSP33.events.to_client.MVEventVisitor;
import it.polimi.ingsw.PSP33.events.to_client.MVEvent;

public class CurrentPlayer implements MVEvent {

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
