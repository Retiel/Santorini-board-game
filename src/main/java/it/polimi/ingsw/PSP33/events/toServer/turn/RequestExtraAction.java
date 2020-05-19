package it.polimi.ingsw.PSP33.events.toServer.turn;

import it.polimi.ingsw.PSP33.events.VCEventVisitor;
import it.polimi.ingsw.PSP33.events.toServer.VCEvent;

public class RequestExtraAction implements VCEvent {

    @Override
    public void accept(VCEventVisitor VCEventVisitor) {
        VCEventVisitor.visit(this);
    }
}
