package view;

import message.VisitorClientMessageInterface;
import message.client.ClientMessage;
import message.server.ServerMessage;
import utils.patterns.observable.Observable;
import utils.patterns.observable.Observer;

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
