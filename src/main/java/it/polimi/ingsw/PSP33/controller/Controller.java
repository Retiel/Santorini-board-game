package it.polimi.ingsw.PSP33.controller;

import it.polimi.ingsw.PSP33.controller.rules.TurnAction;
import it.polimi.ingsw.PSP33.controller.rules.move.MoveContext;
import it.polimi.ingsw.PSP33.controller.rules.TurnManager;
import it.polimi.ingsw.PSP33.message.VisitorServerMessageInterface;
import it.polimi.ingsw.PSP33.message.server.ServerMessage;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.utils.patterns.observable.Observer;

import java.util.List;


public class Controller implements Observer<ServerMessage>, VisitorServerMessageInterface {

    private TurnManager turnManager;
    private TurnAction turnAction;

    public Controller(Model model) {
        this.turnManager = new TurnManager(model);
        this.turnAction = new TurnAction(model);

    }

    public void moveCheck(){
        MoveContext moveContext = new MoveContext("yolo");
        List<Cell> cellList1 = moveContext.checkMove(null, null);

    }

    @Override
    public void visit(ServerMessage serverMessage) {

    }

    @Override
    public void update(ServerMessage serverMessage) {
        serverMessage.accept(this);
    }
}