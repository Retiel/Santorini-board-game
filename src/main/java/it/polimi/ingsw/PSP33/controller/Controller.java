package it.polimi.ingsw.PSP33.controller;

import it.polimi.ingsw.PSP33.controller.rulesManager.TurnAction;
import it.polimi.ingsw.PSP33.controller.turnManager.TurnManager;
import it.polimi.ingsw.PSP33.message.VisitorServerMessageInterface;
import it.polimi.ingsw.PSP33.message.server.ServerMessage;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.utils.patterns.observable.Observer;


public class Controller implements Observer<ServerMessage>, VisitorServerMessageInterface {

    private TurnManager turnManager;
    private TurnAction turnAction;

    public Controller(Model model) {
        this.turnManager = new TurnManager(model);
        this.turnAction = new TurnAction(model);

    }

    @Override
    public void visit(ServerMessage serverMessage) {

    }

    @Override
    public void update(ServerMessage serverMessage) {
        serverMessage.accept(this);
    }
}