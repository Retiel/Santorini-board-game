package it.polimi.ingsw.PSP33.events.toClient.setup;

import it.polimi.ingsw.PSP33.events.MVEventVisitor;
import it.polimi.ingsw.PSP33.events.toClient.MVEvent;
import it.polimi.ingsw.PSP33.utils.Coord;

import java.util.List;

public class PossiblePlacement extends MVEvent{

    private final List<Coord> coordList;

    public PossiblePlacement(List<Coord> coordList) {
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
