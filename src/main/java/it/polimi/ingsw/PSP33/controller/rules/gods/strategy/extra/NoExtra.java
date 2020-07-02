package it.polimi.ingsw.PSP33.controller.rules.gods.strategy.extra;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.ArrayList;
import java.util.List;

/**
 * No extra effect exist
 * (Usable as a template to copy/paste)
 */
public class NoExtra implements ExtraAction {

    @Override
    public List<Cell> checkPlusAction(Pawn pawn, Board board) {
        return new ArrayList<>();
    }

    @Override
    public void applyAction(Cell cell, Pawn pawn, Model model, boolean trigger) {

    }
}
