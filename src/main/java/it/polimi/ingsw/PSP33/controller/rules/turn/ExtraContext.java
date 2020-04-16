package it.polimi.ingsw.PSP33.controller.rules.turn;

import it.polimi.ingsw.PSP33.controller.rules._implementation.Demeter;
import it.polimi.ingsw.PSP33.controller.rules._implementation.Hephaestus;
import it.polimi.ingsw.PSP33.controller.rules._implementation.Artemis;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

public class ExtraContext {

    private ExtraAction extraAction;

    public ExtraContext(String godName) {

        switch (godName){
            case "Artemis": this.extraAction = new Artemis();
            case "Demeter": this.extraAction = new Demeter();
            case "Hephaestus": this.extraAction = new Hephaestus();
            default:
               this.extraAction = null;
        }
    }

    public List<Cell> extraExecution(Pawn pawn, Board board){
        if (this.extraAction != null) return extraAction.executePlusAction(pawn, board);
        return null;
    }
}
