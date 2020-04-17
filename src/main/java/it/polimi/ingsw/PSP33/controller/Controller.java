package it.polimi.ingsw.PSP33.controller;

import it.polimi.ingsw.PSP33.controller.rules.SetUpTurn;
import it.polimi.ingsw.PSP33.controller.rules.TurnChange;
import it.polimi.ingsw.PSP33.controller.rules.TurnManager;
import it.polimi.ingsw.PSP33.events.VCEventVisitor;
import it.polimi.ingsw.PSP33.events.toClient.data.DataModel;
import it.polimi.ingsw.PSP33.events.toClient.setup.PossiblePlacement;
import it.polimi.ingsw.PSP33.events.toServer.setup.PlacePawn;
import it.polimi.ingsw.PSP33.events.toServer.VCEvent;
import it.polimi.ingsw.PSP33.events.toServer.VCEventSample;
import it.polimi.ingsw.PSP33.events.toServer.turn.*;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.utils.patterns.observable.Observer;


public class Controller implements Observer<VCEvent>, VCEventVisitor {

    private SetUpTurn setUpTurn;
    private TurnChange turnChange;
    private TurnManager turnManager;

    public Controller(Model model) {
        this.setUpTurn = new SetUpTurn(model);
        this.turnChange = new TurnChange(model);
        this.turnManager = new TurnManager(model);

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

        if(setUpTurn.CheckEndTurn()) turnChange.nextTurn();

        if (setUpTurn.CheckEndSetUp()){

        }else{
            setUpTurn.AskPlayers();
        }
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