package it.polimi.ingsw.PSP33.controller.rules._extraTurn;

import it.polimi.ingsw.PSP33.controller.rules.tools.DataBuffer;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.ArrayList;
import java.util.List;

public class NoExtra implements ExtraAction {

    @Override
    public List<Cell> checkPlusAction(Pawn pawn, Board board, DataBuffer dataBuffer) {
        return new ArrayList<>();
    }

    @Override
    public void applyAction(Cell cell, Pawn pawn, Model model) {

    }
}
