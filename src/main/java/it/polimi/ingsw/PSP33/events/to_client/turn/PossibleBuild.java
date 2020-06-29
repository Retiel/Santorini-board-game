package it.polimi.ingsw.PSP33.events.to_client.turn;

import it.polimi.ingsw.PSP33.events.to_client.MVEventVisitor;
import it.polimi.ingsw.PSP33.events.to_client.MVEvent;
import it.polimi.ingsw.PSP33.utils.Coord;

import java.util.List;

public class PossibleBuild implements MVEvent {

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
        MVEventVisitor.visit(this);
    }
}
