package it.polimi.ingsw.PSP33.view;


import it.polimi.ingsw.PSP33.events.to_client.MVEvent;
import it.polimi.ingsw.PSP33.events.to_client.MVEventVisitor;
import it.polimi.ingsw.PSP33.events.to_server.VCEvent;
import it.polimi.ingsw.PSP33.utils.observable.Observable;
import it.polimi.ingsw.PSP33.utils.observable.Observer;


/**
 * Implementation of the visitor pattern
 * */
public abstract class AbstractView extends Observable<VCEvent> implements Observer<MVEvent>, MVEventVisitor {

    @Override
    public void update(MVEvent clientMessage) {
        clientMessage.accept(this);
    }
}
