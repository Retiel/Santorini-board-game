package it.polimi.ingsw.PSP33.controller.rules.move;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

public class MoveContext {

    private Move move;

    public MoveContext(String godName) {

        switch (godName){
            case "Apollo": this.move = new MoveApollo();
            case "Artemis": this.move = new MoveArtemis();
            case "Minotaur": this.move = new MoveMinotaur();
            default:
                this.move = new MoveBasic();
        }

    }

    public List<Cell> checkMove(Pawn p, Board b){
        return  move.checkMove(p,b);
    }

    public void execMove(int coordX, int coordY, Pawn p, Board b){
        move.executeMove(coordX, coordY, p, b);
    }


}
