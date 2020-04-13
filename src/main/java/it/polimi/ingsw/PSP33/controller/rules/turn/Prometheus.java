package it.polimi.ingsw.PSP33.controller.rules.turn;

import it.polimi.ingsw.PSP33.controller.rules.build.Build;
import it.polimi.ingsw.PSP33.controller.rules.move.Move;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

public class Prometheus implements Move, Build {

    @Override
    public List<Cell> checkBuild(Pawn pawn, Board board) {
        return null;
    }

    @Override
    public void executeBuild(Cell cellToBuild, boolean trigger) {

    }

    @Override
    public List<Cell> checkMove(Pawn pawn, Board board) {
        return null;
    }

    @Override
    public void executeMove(Cell newCell, Pawn pawn, Board board) {

    }
}
