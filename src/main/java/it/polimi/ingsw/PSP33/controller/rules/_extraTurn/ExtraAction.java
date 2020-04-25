package it.polimi.ingsw.PSP33.controller.rules._extraTurn;

import it.polimi.ingsw.PSP33.controller.rules.buffer_control.DataBuffer;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

public interface ExtraAction {
    
    List<Cell> executePlusAction(Pawn pawn, Board board, Cell cell);

    void applyAction(Cell cell, Pawn pawn, Board board);
}
