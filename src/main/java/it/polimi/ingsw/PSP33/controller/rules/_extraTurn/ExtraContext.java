package it.polimi.ingsw.PSP33.controller.rules._extraTurn;

import it.polimi.ingsw.PSP33.controller.rules.__implementation.Demeter;
import it.polimi.ingsw.PSP33.controller.rules.__implementation.Hephaestus;
import it.polimi.ingsw.PSP33.controller.rules.__implementation.Artemis;
import it.polimi.ingsw.PSP33.controller.rules.__implementation.Prometheus;
import it.polimi.ingsw.PSP33.controller.rules.buffer_control.DataBuffer;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;
import it.polimi.ingsw.PSP33.utils.Coord;

import java.util.List;

public class ExtraContext {

    private ExtraAction extraAction;

    public ExtraContext(String godName) {

        switch (godName){
            case "Artemis": this.extraAction = new Artemis(); break;
            case "Demeter": this.extraAction = new Demeter(); break;
            case "Hephaestus": this.extraAction = new Hephaestus(); break;
            case "Prometheus": this.extraAction = new Prometheus(); break;
            default:
               this.extraAction = new NoExtra();
        }
    }

    public List<Cell> extraRequest(Pawn pawn, Board board, DataBuffer dataBuffer){
        if (this.extraAction != null) return extraAction.checkPlusAction(pawn, board, dataBuffer);
        return null;
    }

    public void ExecAction(Coord coord, Pawn pawn, Model model){
        Cell cell =  model.getBoard().getGrid()[coord.getX()][coord.getY()];
        extraAction.applyAction(cell, pawn, model);
    }
}
