package it.polimi.ingsw.PSP33.controller.rules.build;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

public interface Build {

    List<Cell> checkBuild(Pawn pawn, Board board);

    void executeBuild(Board board, int x, int y);
}
