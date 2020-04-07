package it.polimi.ingsw.PSP33.controller.godsRules;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

public class MoveContext {

    private Move move;

    public MoveContext(String godName) {

        switch (godName){
            case "Apollo": this.move = new MoveApollo();
            default:
                throw new IllegalStateException("Unexpected value: " + godName);
        }

    }

    public List<Cell> execute(Pawn p, Board b){
        return  move.checkMove(p,b);
    }
}
