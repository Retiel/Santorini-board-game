package it.polimi.ingsw.PSP33.controller.rules.turn;

import it.polimi.ingsw.PSP33.controller.rules._implementation.Demeter;
import it.polimi.ingsw.PSP33.controller.rules._implementation.Hephaestus;
import it.polimi.ingsw.PSP33.controller.rules._implementation.Artemis;
import it.polimi.ingsw.PSP33.controller.rules._implementation.Prometheus;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;
import it.polimi.ingsw.PSP33.utils.Coord;

import java.util.List;

public class ExtraContext {

    private ExtraAction extraAction;

    public ExtraContext(String godName) {

        switch (godName){
            case "Artemis": this.extraAction = new Artemis();
            case "Demeter": this.extraAction = new Demeter();
            case "Hephaestus": this.extraAction = new Hephaestus();
            case "Prometheus": this.extraAction = new Prometheus();
            default:
               this.extraAction = null;
        }
    }

    public List<Cell> extraRequest(Pawn pawn, Board board){
        if (this.extraAction != null) return extraAction.executePlusAction(pawn, board);
        return null;
    }

    public void ExecAction(Coord coord, Pawn pawn, Board board){
        Cell cell =  board.getGrid()[coord.getX()][coord.getY()];
        extraAction.applyAction(cell, pawn, board);
    }
}
