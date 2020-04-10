package it.polimi.ingsw.PSP33.controller.rules.move;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

public class MoveContext {

    private Move move;

    public MoveContext(String godName) {
        switch (godName){
            case "APOLLO":
                break;

            default:
                this.move = new MoveDefault();
                throw new IllegalStateException("Unexpected value: " + godName);
        }
    }

    public List<Cell> check(Pawn pawn, Board board) {
        return move.checkMove(pawn, board);
    }

    public void execute(Pawn pawn, Board board, int x, int y) {
        move.executeMove(pawn, board, x, y);
    }
}
