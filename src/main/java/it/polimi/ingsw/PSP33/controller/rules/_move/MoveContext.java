package it.polimi.ingsw.PSP33.controller.rules._move;

import it.polimi.ingsw.PSP33.controller.rules.__implementation.Apollo;
import it.polimi.ingsw.PSP33.controller.rules.__implementation.Minotaur;
import it.polimi.ingsw.PSP33.controller.rules.__implementation.Prometheus;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

public class MoveContext {

    private Move move;
    private String context;

    public MoveContext(String godName) {
        this.context = godName;
        switch (godName){
            case "Apollo": this.move = new Apollo(); break;
            case "Minotaur": this.move = new Minotaur(); break;
            case "Prometheus": this.move = new Prometheus(); break;
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
