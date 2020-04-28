package it.polimi.ingsw.PSP33.controller;

import it.polimi.ingsw.PSP33.controller.rules.SetUpManager;
import it.polimi.ingsw.PSP33.controller.rules.TurnManager;
import it.polimi.ingsw.PSP33.events.VCEventVisitor;
import it.polimi.ingsw.PSP33.events.toClient.MVEventSample;
import it.polimi.ingsw.PSP33.events.toServer.setup.PlacePawn;
import it.polimi.ingsw.PSP33.events.toServer.VCEvent;
import it.polimi.ingsw.PSP33.events.toServer.VCEventSample;
import it.polimi.ingsw.PSP33.events.toServer.turn.*;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.utils.patterns.observable.Observer;


public class Controller implements Observer<VCEvent>, VCEventVisitor {

    private SetUpManager setUpManager;
    private TurnManager turnManager;

    public Controller(Model model) {
        this.setUpManager = new SetUpManager(model);
        this.turnManager = new TurnManager(model);

        this.setUpManager.SetStartingPlayer();
    }

    @Override
    public void visit(VCEventSample vcEventSample) {
        turnManager.notifyView(new MVEventSample());
    }

    @Override
    public void visit(PlacePawn placePawn) {

        int coordX = placePawn.getCoord().getX();
        int coordY = placePawn.getCoord().getY();

        setUpManager.PlacePlayerPawn(coordX, coordY);

        if(setUpManager.CheckEndTurn()) setUpManager.nextTurn();

        if (setUpManager.CheckEndSetUp()){
            turnManager.newTurnContext();
        }else{
            setUpManager.AskPlayers();
        }
    }

    @Override
    public void visit(NewTurn newTurn) {
        turnManager.nextTurn();
        turnManager.newTurnContext();
    }

    @Override
    public void visit(MoveAction moveAction) {
        turnManager.execMove(moveAction.getCoord());
    }

    @Override
    public void visit(BuildAction buildAction) {
        turnManager.execBuild(buildAction.getCoord(), buildAction.isRoof());
    }

    @Override
    public void visit(ExtraAction extraAction) {
        turnManager.execExtra(extraAction.getCoord());
    }

    @Override
    public void visit(RequestPossibleMove requestPossibleMove) {
        turnManager.moveFlow(requestPossibleMove.getPawn());
    }

    @Override
    public void visit(RequestPossibleBuild requestPossibleBuild) {
        turnManager.buildFlow(requestPossibleBuild.getPawn());
    }

    @Override
    public void visit(RequestExtraAction requestExtraAction) {
        turnManager.extraActionFlow(requestExtraAction.getPawn());
    }

    @Override
    public void update(VCEvent serverMessage) {
        serverMessage.accept(this);
    }


}