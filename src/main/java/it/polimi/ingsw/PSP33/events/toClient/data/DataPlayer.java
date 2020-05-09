package it.polimi.ingsw.PSP33.events.toClient.data;

import it.polimi.ingsw.PSP33.events.MVEventVisitor;
import it.polimi.ingsw.PSP33.events.toClient.MVEvent;
import it.polimi.ingsw.PSP33.model.light_version.LightPlayer;

public class DataPlayer implements MVEvent {

    private final LightPlayer player;

    public DataPlayer(LightPlayer player) {
        this.player = player;
    }

    public LightPlayer getPlayer() {
        return player;
    }

    @Override
    public void accept(MVEventVisitor MVEventVisitor) {
        MVEventVisitor.visit(this);
    }
}
