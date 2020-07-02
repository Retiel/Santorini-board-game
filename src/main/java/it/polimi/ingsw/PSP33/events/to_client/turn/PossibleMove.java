package it.polimi.ingsw.PSP33.events.to_client.turn;

import it.polimi.ingsw.PSP33.events.to_client.MVEvent;
import it.polimi.ingsw.PSP33.events.to_client.MVEventVisitor;
import it.polimi.ingsw.PSP33.utils.Coord;

import java.util.List;

/**
 * Possible move event
 */
public class PossibleMove implements MVEvent {

    /**
     * List of available default move coordinates
     */
    private final List<Coord> coordList;

    /**
     * List of available god move coordinates
     */
    private final List<Coord> godsList;

    public PossibleMove(List<Coord> coordList, List<Coord> godsList) {
        this.coordList = coordList;
        this.godsList = godsList;
    }

    public List<Coord> getCoordList() {
        return coordList;
    }

    public List<Coord> getGodsList() {
        return godsList;
    }

    @Override
    public void accept(MVEventVisitor MVEventVisitor) {
        MVEventVisitor.visit(this);
    }
}
