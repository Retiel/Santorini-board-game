package it.polimi.ingsw.PSP33.controller.rules.gods.strategy.extra;

import it.polimi.ingsw.PSP33.controller.rules.tools.DataBuffer;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

public interface ExtraAction {
    
    List<Cell> checkPlusAction(Pawn pawn, Board board, DataBuffer dataBuffer);

    void applyAction(Cell cell, Pawn pawn, Model model);
}
