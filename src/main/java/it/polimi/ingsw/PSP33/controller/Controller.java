package it.polimi.ingsw.PSP33.controller;

import it.polimi.ingsw.PSP33.controller.rules.SetUpManager;
import it.polimi.ingsw.PSP33.controller.rules.TurnManager;
import it.polimi.ingsw.PSP33.events.to_server.VCEvent;
import it.polimi.ingsw.PSP33.events.to_server.VCEventVisitor;
import it.polimi.ingsw.PSP33.events.to_server.setup.ChoosenGod;
import it.polimi.ingsw.PSP33.events.to_server.setup.PlacePawn;
import it.polimi.ingsw.PSP33.events.to_server.setup.PlayerDisconnected;
import it.polimi.ingsw.PSP33.events.to_server.setup.SelectedGods;
import it.polimi.ingsw.PSP33.events.to_server.turn.*;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.utils.observable.Observer;


public class Controller implements Observer<VCEvent>, VCEventVisitor {

    private final SetUpManager setUpManager;
    private final TurnManager turnManager;

    public Controller(Model model) {
        this.setUpManager = new SetUpManager(model);
        this.turnManager = new TurnManager(model);
    }

    public void startGame(){
        setUpManager.setStartingPlayer();
        setUpManager.askPlayers();
    }

    @Override
    public void visit(SelectedGods selectedGods) {
        setUpManager.setGods(selectedGods.getGods());
        setUpManager.nextTurn();
        setUpManager.playersChooseGod();
    }

    @Override
    public void visit(SelectedPawn selectedPawn) {
        turnManager.setCurrentPawn(selectedPawn.getPawn());
        turnManager.newAction();
    }

    @Override
    public void visit(ChoosenGod choosenGod) {
        setUpManager.setGodforPlayer(choosenGod.getGod());
        setUpManager.nextTurn();

        if (setUpManager.CheckCardSetUp()){
            setUpManager.sendDataPlayers();
            turnManager.newTurnContext();
        }else{
            setUpManager.playersChooseGod();
        }

    }

    @Override
    public void visit(PlacePawn placePawn) {

        int coordX = placePawn.getCoord().getX();
        int coordY = placePawn.getCoord().getY();


        setUpManager.PlacePlayerPawn(coordX, coordY);

        if(setUpManager.CheckEndTurn()){
            setUpManager.nextTurn();
        }

        if (setUpManager.CheckPawnSetUp()){
            setUpManager.askGods();
        }else{
            setUpManager.askPlayers();
        }
    }

    @Override
    public void visit(PlayerDisconnected playerDisconnected) {
        turnManager.resetLimiters(playerDisconnected.getName());
        turnManager.removePlayer(playerDisconnected.getName());
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
        turnManager.execExtra(extraAction.getCoord(), extraAction.isUse());
    }

    @Override
    public void visit(RequestPossibleMove requestPossibleMove) {
        turnManager.moveFlow();
    }

    @Override
    public void visit(RequestPossibleBuild requestPossibleBuild) {
        turnManager.buildFlow();
    }

    @Override
    public void visit(RequestExtraAction requestExtraAction) {
        turnManager.extraActionFlow();
    }

    @Override
    public void update(VCEvent serverMessage) {
        serverMessage.accept(this);
    }


    /*Debugging tools*/

    public SetUpManager getSetUpManager() {
        return setUpManager;
    }

    public TurnManager getTurnManager() {
        return turnManager;
    }
}