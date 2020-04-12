package it.polimi.ingsw.PSP33.controller.rules.win;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

public class WinContext {

    private WinCondition winCondition;

    public WinContext(String godName) {

        switch (godName){
            case "Pan": this.winCondition = new WinPan();
            case "": this.winCondition = new WinBasic();
            default:
                this.winCondition = new WinBasic();
        }

    }

    public boolean verifyLose(List<Cell> cellList){
        return cellList.size() == 0;
    }

    public void checkWinCondition(Board board, Pawn pawn, Cell newCell){
        winCondition.verifyWin(newCell, board, pawn);
    }


}
