package it.polimi.ingsw.PSP33.controller;

import it.polimi.ingsw.PSP33.controller.turnManager.TurnManager;
import it.polimi.ingsw.PSP33.message.VisitorServerMessageInterface;
import it.polimi.ingsw.PSP33.message.server.ServerMessage;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.utils.Observer;


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