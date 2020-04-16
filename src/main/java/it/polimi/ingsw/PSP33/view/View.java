package it.polimi.ingsw.PSP33.view;

import it.polimi.ingsw.PSP33.events.toClient.MVEvent;
import it.polimi.ingsw.PSP33.events.toClient.MVEventSample;
import it.polimi.ingsw.PSP33.utils.patterns.observable.Observable;
import it.polimi.ingsw.PSP33.events.MVEventVisitor;
import it.polimi.ingsw.PSP33.events.toServer.VCEvent;
import it.polimi.ingsw.PSP33.utils.patterns.observable.Observer;

/**
 * Implementation of the visitor pattern
 * */
public class View extends Observable<VCEvent> implements Observer<MVEvent>, MVEventVisitor {

    @Override
    public void update(MVEvent mvEvent) {
        mvEvent.accept(this);
    }

    @Override
    public void visit(MVEventSample mvEventSample) {

    }
}
