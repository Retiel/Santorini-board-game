package it.polimi.ingsw.PSP33.events.to_client.setup;

import it.polimi.ingsw.PSP33.events.to_client.MVEvent;
import it.polimi.ingsw.PSP33.events.to_client.MVEventVisitor;
import it.polimi.ingsw.PSP33.utils.Coord;

import java.util.List;

/**
 * Possible placement event
 */
public class PossiblePlacement implements MVEvent{

    /**
     * List of available placement coordinates
     */
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
