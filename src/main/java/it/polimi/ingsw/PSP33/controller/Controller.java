package it.polimi.ingsw.PSP33.controller;

import it.polimi.ingsw.PSP33.controller.rules.SetUpTurn;
import it.polimi.ingsw.PSP33.controller.rules.TurnControl;
import it.polimi.ingsw.PSP33.controller.rules.TurnFlow;
import it.polimi.ingsw.PSP33.events.VCEventVisitor;
import it.polimi.ingsw.PSP33.events.toServer.setup.PlacePawn;
import it.polimi.ingsw.PSP33.events.toServer.VCEvent;
import it.polimi.ingsw.PSP33.events.toServer.VCEventSample;
import it.polimi.ingsw.PSP33.events.toServer.turn.*;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.utils.patterns.observable.Observer;


public class Controller implements Observer<VCEvent>, VCEventVisitor {

    private SetUpTurn setUpTurn;
    private TurnControl turnControl;
    private TurnFlow turnFlow;

    public Controller(Model model) {
        this.setUpTurn = new SetUpTurn(model);
        this.turnControl = new TurnControl(model);
        this.turnFlow = new TurnFlow(model);

        this.setUpTurn.SetStartingPlayer();
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

        int coordX = placePawn.getCoord().getX();
        int coordY = placePawn.getCoord().getY();

        setUpTurn.PlacePlayerPawn(coordX, coordY);

        if(setUpTurn.CheckEndTurn()) turnControl.nextTurn();

        if (setUpTurn.CheckEndSetUp()){
            turnFlow.NewTurnContext();
        }else{
            setUpTurn.AskPlayers();
        }
    }

    @Override
    public void visit(MoveAction moveAction) {
        turnFlow.ExecMove(moveAction.getCoord());

    }

    @Override
    public void visit(BuildAction buildAction) {
        turnFlow.ExecBuild(buildAction.getCoord(), buildAction.isRoof());

    }

    @Override
    public void visit(ExtraAction extraAction) {
        turnFlow.ExecExtra(extraAction.getCoord());
    }

    @Override
    public void visit(RequestPossibleMove requestPossibleMove) {
        turnFlow.MoveFlow(requestPossibleMove.getPawn());
    }

    @Override
    public void visit(RequestPossibleBuild requestPossibleBuild) {
        turnFlow.BuildFlow(requestPossibleBuild.getPawn());
    }

    @Override
    public void visit(RequestExtraAction requestExtraAction) {
        turnFlow.ExtraActionFlow(requestExtraAction.getPawn());
    }


}