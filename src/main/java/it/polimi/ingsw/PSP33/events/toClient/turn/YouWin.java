package it.polimi.ingsw.PSP33.events.toClient.turn;

import it.polimi.ingsw.PSP33.events.MVEventVisitor;
import it.polimi.ingsw.PSP33.events.toClient.MVEvent;

public class YouWin implements MVEvent {

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
