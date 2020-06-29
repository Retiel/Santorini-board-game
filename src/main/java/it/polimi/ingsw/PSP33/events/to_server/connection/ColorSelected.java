package it.polimi.ingsw.PSP33.events.to_server.connection;

import it.polimi.ingsw.PSP33.events.to_server.SCEvent;
import it.polimi.ingsw.PSP33.events.to_server.SCEventVisitor;
import it.polimi.ingsw.PSP33.utils.enums.Color;

public class ColorSelected implements SCEvent {

    private final Color color;

    public ColorSelected(Color color) {
        this.color = color;
    }

    public Color getColor() {
        return color;
    }

    @Override
    public void accept(SCEventVisitor scEventVisitor) {
        scEventVisitor.visit(this);
    }
}
