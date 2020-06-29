package it.polimi.ingsw.PSP33.events.to_client.turn;

import it.polimi.ingsw.PSP33.events.to_client.MVEventVisitor;
import it.polimi.ingsw.PSP33.events.to_client.MVEvent;
import it.polimi.ingsw.PSP33.utils.Coord;

import java.util.List;

public class PossibleExtraAction implements MVEvent {

    private final List<Coord> coordList;

    public PossibleExtraAction(List<Coord> coordList) {
        this.coordList = coordList;
    }

    public List<Coord> getCoordList() {
        return coordList;
    }

    @Override
    public void accept(MVEventVisitor MVEventVisitor) {
        MVEventVisitor.visit(this);
    }
}
