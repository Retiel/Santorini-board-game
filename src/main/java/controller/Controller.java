package controller;

import message.VisitorServerMessageInterface;
import message.server.ServerMessage;
import model.Model;
import utils.Observer;

public class Controller implements Observer<ServerMessage>, VisitorServerMessageInterface {

    private TurnManager turnManager;

    public Controller(Model model) {
        this.turnManager = new TurnManager(model);

    }

    @Override
    public void visit(ServerMessage serverMessage) {

    }

    @Override
    public void update(ServerMessage serverMessage) {
        serverMessage.accept(this);
    }
}