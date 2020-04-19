package it.polimi.ingsw.PSP33.controller.rules._implementation;

import it.polimi.ingsw.PSP33.controller.rules.BasicAction;
import it.polimi.ingsw.PSP33.controller.rules.GetCell;
import it.polimi.ingsw.PSP33.controller.rules.build.Build;
import it.polimi.ingsw.PSP33.controller.rules.move.Move;
import it.polimi.ingsw.PSP33.controller.rules.turn.ExtraAction;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;
import java.util.stream.Collectors;

public class Prometheus implements Move, Build, ExtraAction {

    private boolean effectOn = false;

    @Override
    public List<Cell> checkBuild(Pawn pawn, Board board) {
        return GetCell.getBuildableCells(pawn, board);
    }

    @Override
    public void executeBuild(Cell cellToBuild, boolean trigger) {
        effectOn = trigger;
        BasicAction.BuildBlock(cellToBuild);
    }

    @Override
    public List<Cell> checkMove(Pawn pawn, Board board) {

        List<Cell> cellList = GetCell.getMovableCells(pawn, board);
        if (effectOn){
            Cell current = board.getGrid()[pawn.getCoordX()][pawn.getCoordY()];
            cellList = cellList.stream().filter(c -> c.getFloor() <= current.getFloor()).collect(Collectors.toList());
        }
        return cellList;
    }

    @Override
    public void executeMove(Cell newCell, Pawn pawn, Board board) {
        Cell oldCell = board.getGrid()[pawn.getCoordX()][pawn.getCoordY()];
        BasicAction.MovePawn(oldCell, newCell, pawn);
    }

    @Override
    public List<Cell> executePlusAction(Pawn pawn, Board board) {
        return checkBuild(pawn, board);
    }

    @Override
    public void applyAction(Cell cell, Pawn pawn, Board board) {
        executeBuild(cell, true);
    }
}
