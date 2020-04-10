package it.polimi.ingsw.PSP33.controller.rules.build;

import it.polimi.ingsw.PSP33.controller.rules.Tools;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;
import java.util.stream.Collectors;

public class BuildDefault implements Build {

    @Override
    public List<Cell> checkBuild(Pawn pawn, Board board) {
        List<Cell> adiacent = Tools.getAdjacentCells(pawn, board);

        return adiacent.stream().filter(c ->
                c.getOccupied() == null
                        && !c.isRoof())
                .collect(Collectors.toList());
    }

    @Override
    public void executeBuild(Board board, int x, int y) {
        Cell cell = board.getCellByCoordinates(x, y);

        if(cell.getFloor()==3){
            cell.setRoof(true);
        } else {
            cell.setFloor(cell.getFloor()+1);
        }
    }
}
