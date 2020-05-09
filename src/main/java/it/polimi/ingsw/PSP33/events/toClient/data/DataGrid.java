package it.polimi.ingsw.PSP33.events.toClient.data;

import it.polimi.ingsw.PSP33.events.MVEventVisitor;
import it.polimi.ingsw.PSP33.events.toClient.MVEvent;
import it.polimi.ingsw.PSP33.model.light_version.LightBoard;

public class DataGrid implements MVEvent {

    private final LightBoard lightBoard;

    public DataGrid(LightBoard lightBoard) {
        this.lightBoard = lightBoard;
    }

    public LightBoard getGrid() {
        return lightBoard;
    }

    @Override
    public void accept(MVEventVisitor MVEventVisitor) {
        MVEventVisitor.visit(this);
    }
}
