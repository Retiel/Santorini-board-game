package it.polimi.ingsw.PSP33.controller.rules.move;

import it.polimi.ingsw.PSP33.controller.rules._implementation.Apollo;
import it.polimi.ingsw.PSP33.controller.rules._implementation.Artemis;
import it.polimi.ingsw.PSP33.controller.rules._implementation.Minotaur;
import it.polimi.ingsw.PSP33.controller.rules._implementation.Prometheus;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

public class MoveContext {

    private Move move;

    public MoveContext(String godName) {

        switch (godName){
            case "Apollo": this.move = new Apollo();
            case "Artemis": this.move = new Artemis();
            case "Minotaur": this.move = new Minotaur();
            case "Prometheus": this.move = new Prometheus();
            default:
                this.move = new MoveBasic();
        }

    }

    public List<Cell> checkMove(Pawn p, Board b){
        return  move.checkMove(p,b);
    }

    public void execMove(int coordX, int coordY, Pawn p, Board b){
        Cell newCell = b.getGrid()[coordX][coordY];
        move.executeMove(newCell, p, b);
    }


}
