package it.polimi.ingsw.PSP33.controller;

import it.polimi.ingsw.PSP33.controller.rules.BasicAction;
import it.polimi.ingsw.PSP33.controller.rules.TurnManager;
import it.polimi.ingsw.PSP33.controller.rules.move.MoveContext;
import it.polimi.ingsw.PSP33.events.VCEventVisitor;
import it.polimi.ingsw.PSP33.events.toServer.setup.PlacePawn;
import it.polimi.ingsw.PSP33.events.toServer.VCEvent;
import it.polimi.ingsw.PSP33.events.toServer.VCEventSample;
import it.polimi.ingsw.PSP33.events.toServer.turn.*;
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

    }

    @Override
    public void visit(BuildAction buildAction) {

    }

    @Override
    public void visit(MoveAction moveAction) {

    }

    @Override
    public void visit(RequestExtraAction requestExtraAction) {

    }

    @Override
    public void visit(RequestPossibleMove requestPossibleMove) {

    }

    @Override
    public void visit(RequestPossibleBuild requestPossibleBuild) {

    }
}