package it.polimi.ingsw.PSP33.events.to_client.turn;

import it.polimi.ingsw.PSP33.events.to_client.MVEventVisitor;
import it.polimi.ingsw.PSP33.events.to_client.MVEvent;
import it.polimi.ingsw.PSP33.utils.Coord;

import java.util.List;

/**
 * Possible build event
 */
public class PossibleBuild implements MVEvent {

    /**
     * List of available default build coordinates
     */
    private final List<Coord> coordList;

    /**
     * List of available god build coordinates
     */
    private final List<Coord> godsList;

    /**
     * Boolean for possible roof build
     */
    private final boolean roofAvailable;

    public PossibleBuild(List<Coord> coordList, List<Coord> godsList, boolean roofAvailable) {
        this.coordList = coordList;
        this.godsList = godsList;
        this.roofAvailable = roofAvailable;
    }

    public List<Coord> getCoordList() {
        return coordList;
    }

    public List<Coord> getGodsList() {
        return godsList;
    }

    public boolean isRoofAvailable() {
        return roofAvailable;
    }

    @Override
    public void accept(MVEventVisitor MVEventVisitor) {
        MVEventVisitor.visit(this);
    }
}
