package it.polimi.ingsw.PSP33.events.toClient.turn;

import it.polimi.ingsw.PSP33.events.MVEventVisitor;
import it.polimi.ingsw.PSP33.events.toClient.MVEvent;
import it.polimi.ingsw.PSP33.utils.Coord;

import java.util.List;

public class PossibleMove implements MVEvent {

    private final List<Coord> coordList;
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
