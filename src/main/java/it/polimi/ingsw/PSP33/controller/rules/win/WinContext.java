package it.polimi.ingsw.PSP33.controller.rules.win;

import it.polimi.ingsw.PSP33.controller.rules._implementation.Pan;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

public class WinContext {

    private WinCondition winCondition;


    public WinContext(String godName) {

        switch (godName){
            case "Pan": this.winCondition = new Pan();
            case "": this.winCondition = new WinBasic();
            default:
                this.winCondition = new WinBasic();
        }

    }

    public boolean verifyLose(List<Cell> cellList){
        return cellList.size() == 0;
    }

    public boolean checkWinCondition(Board board, Pawn pawn, Cell newCell){
        return winCondition.verifyWin(newCell, board, pawn);
    }


}
