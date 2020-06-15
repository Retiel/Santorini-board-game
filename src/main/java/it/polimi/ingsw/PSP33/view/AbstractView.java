package it.polimi.ingsw.PSP33.view;


import it.polimi.ingsw.PSP33.events.MVEventVisitor;
import it.polimi.ingsw.PSP33.events.toClient.MVEvent;
import it.polimi.ingsw.PSP33.events.toServer.VCEvent;
import it.polimi.ingsw.PSP33.utils.observable.Observable;
import it.polimi.ingsw.PSP33.utils.observable.Observer;

import java.util.concurrent.Executor;


/**
 * Implementation of the visitor pattern
 * */
public abstract class AbstractView extends Observable<VCEvent> implements Observer<MVEvent>, MVEventVisitor {

    @Override
    public void update(MVEvent clientMessage) {
        clientMessage.accept(this);
    }
}
