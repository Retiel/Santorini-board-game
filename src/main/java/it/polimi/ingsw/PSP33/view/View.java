package it.polimi.ingsw.PSP33.view;

<<<<<<< HEAD:src/main/java/view/View.java
import message.VisitorClientMessageInterface;
import message.client.ClientMessage;
import message.server.ServerMessage;
import utils.patterns.observable.Observable;
import utils.patterns.observable.Observer;
=======
import it.polimi.ingsw.PSP33.message.VisitorClientMessageInterface;
import it.polimi.ingsw.PSP33.message.client.ClientMessage;
import it.polimi.ingsw.PSP33.message.server.ServerMessage;
import it.polimi.ingsw.PSP33.utils.Observable;
import it.polimi.ingsw.PSP33.utils.Observer;
>>>>>>> temp:src/main/java/it/polimi/ingsw/PSP33/view/View.java

/**
 * Implementation of the visitor pattern
 * */
public class View extends Observable<ServerMessage> implements Observer<ClientMessage>, VisitorClientMessageInterface {

    @Override
    public void update(ClientMessage clientMessage) {
        clientMessage.accept(this);
    }

    @Override
    public void visit(ClientMessage clientMessage) {

    }
}
