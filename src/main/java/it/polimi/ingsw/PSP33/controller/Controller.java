package it.polimi.ingsw.PSP33.controller;

import it.polimi.ingsw.PSP33.controller.rules.BasicAction;
import it.polimi.ingsw.PSP33.controller.rules.TurnManager;
import it.polimi.ingsw.PSP33.controller.rules.move.MoveContext;
import it.polimi.ingsw.PSP33.events.VCEventVisitor;
import it.polimi.ingsw.PSP33.events.vcevent.PlacePawn;
import it.polimi.ingsw.PSP33.events.vcevent.VCEvent;
import it.polimi.ingsw.PSP33.events.vcevent.VCEventSample;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.utils.Coord;
import it.polimi.ingsw.PSP33.utils.patterns.observable.Observer;

import java.util.List;


public class Controller implements Observer<VCEvent>, VCEventVisitor {

    private TurnManager turnManager;
    private BasicAction basicAction;

    public Controller(Model model) {
        this.turnManager = new TurnManager(model);
        this.basicAction = new BasicAction(model);

        //TODO: turnManage.setStartingPlayer()
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

    @Override
    public void visit(PlacePawn placePawn) {
        Coord coord = placePawn.getCoord();
        Coord coord1 = new Coord();
    }
}