package it.polimi.ingsw.PSP33.controller;

import it.polimi.ingsw.PSP33.controller.rules.TurnManager;
import it.polimi.ingsw.PSP33.message.VCEventVisitor;
import it.polimi.ingsw.PSP33.message.server.VCEvent;
import it.polimi.ingsw.PSP33.message.server.VCEventSample;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.utils.patterns.observable.Observer;


public class Controller implements Observer<VCEvent>, VCEventVisitor {

    private TurnManager turnManager;

    public Controller(Model model) {
        this.turnManager = new TurnManager(model);

    }

    @Override
    public void visit(VCEventSample vcEventSample) {

    }

    @Override
    public void update(VCEvent serverMessage) {
        serverMessage.accept(this);
    }
}