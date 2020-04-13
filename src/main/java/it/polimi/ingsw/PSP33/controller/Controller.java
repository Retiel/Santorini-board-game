package it.polimi.ingsw.PSP33.controller;

import it.polimi.ingsw.PSP33.controller.rulesManager.TurnAction;
import it.polimi.ingsw.PSP33.controller.turnManager.TurnManager;
import it.polimi.ingsw.PSP33.message.VisitorServerMessageInterface;
import it.polimi.ingsw.PSP33.message.server.ServerMessage;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.utils.patterns.observable.Observer;

import java.util.List;


public class Controller implements Observer<VCEvent>, VCEventVisitor {

    private TurnManager turnManager;
    private BasicAction basicAction;

    public Controller(Model model) {
        this.turnManager = new TurnManager(model);
        this.basicAction = new BasicAction(model);

    }

    public void moveCheck(){
        MoveContext moveContext = new MoveContext("yolo");
        List<Cell> cellList1 = moveContext.checkMove(null, null);

    }

    @Override
    public void visit(VCEventSample vcEventSample) {

    }

    @Override
    public void update(VCEvent serverMessage) {
        serverMessage.accept(this);
    }
}