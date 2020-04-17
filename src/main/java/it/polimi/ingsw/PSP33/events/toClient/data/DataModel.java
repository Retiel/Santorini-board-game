package it.polimi.ingsw.PSP33.events.toClient.data;

import it.polimi.ingsw.PSP33.events.MVEventVisitor;
import it.polimi.ingsw.PSP33.events.toClient.MVEvent;
import it.polimi.ingsw.PSP33.model.Model;

public class DataModel extends MVEvent {

    private final Model model;

    public DataModel(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    @Override
    public void accept(MVEventVisitor MVEventVisitor) {

    }
}
