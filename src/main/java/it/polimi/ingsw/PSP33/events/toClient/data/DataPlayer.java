package it.polimi.ingsw.PSP33.events.toClient.data;

import it.polimi.ingsw.PSP33.events.MVEventVisitor;
import it.polimi.ingsw.PSP33.events.toClient.MVEvent;
import it.polimi.ingsw.PSP33.model.light_version.LightPlayer;

import java.util.List;

public class DataPlayer implements MVEvent {

    private final List<LightPlayer> players;

    private String name;

    public DataPlayer(List<LightPlayer> players) {
        this.players = players;
    }

    public List<LightPlayer> getPlayer() {
        return players;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public void accept(MVEventVisitor MVEventVisitor) {
        MVEventVisitor.visit(this);
    }
}
