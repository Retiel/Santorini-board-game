package it.polimi.ingsw.PSP33.controller.godsRules;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

public interface Build {

    List<Cell> checkBuild(Pawn p, Board b);

    void executeBuild(int x, int y, Board b);
}
