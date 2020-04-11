package it.polimi.ingsw.PSP33.controller.rules.build;

import it.polimi.ingsw.PSP33.controller.rules.TurnAction;
import it.polimi.ingsw.PSP33.controller.rules.Tools;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;
import java.util.stream.Collectors;

public class BuildBasic implements Build {

    @Override
    public List<Cell> checkBuild(Pawn p, Board b) {
        List<Cell> adiacent = Tools.getAdjacentCells(p, b.getGrid(), b.getSIZE());

        return adiacent.stream().filter(c -> c.getOccupied() == null && !c.isRoof()).collect(Collectors.toList());
    }

    @Override
    public void executeBuild(int x, int y, Board b) {
        TurnAction.BuildBlock(b.getGrid()[x][y]);
    }
}
