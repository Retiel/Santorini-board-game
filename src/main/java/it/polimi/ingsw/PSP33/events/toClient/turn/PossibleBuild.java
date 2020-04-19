package it.polimi.ingsw.PSP33.events.toClient.turn;

import it.polimi.ingsw.PSP33.events.MVEventVisitor;
import it.polimi.ingsw.PSP33.events.toClient.MVEvent;
import it.polimi.ingsw.PSP33.utils.Coord;

import java.util.List;

public class PossibleBuild extends MVEvent {

    private final List<Coord> coordList;
    private final List<Coord> godsList;
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

    }
}
