package it.polimi.ingsw.PSP33.events.to_client.connection;

import it.polimi.ingsw.PSP33.events.to_client.CCEvent;
import it.polimi.ingsw.PSP33.events.to_client.CCEventVisitor;
import it.polimi.ingsw.PSP33.utils.enums.Color;

import java.util.List;

public class SelectColor implements CCEvent {

    private final List<Color> colors;

    public SelectColor(List<Color> colors) {
        this.colors = colors;
    }

    public List<Color> getColors() {
        return colors;
    }

    @Override
    public void accept(CCEventVisitor ccEventVisitor) {
        ccEventVisitor.visit(this);
    }
}
