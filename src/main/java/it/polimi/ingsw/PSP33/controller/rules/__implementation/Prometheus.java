package it.polimi.ingsw.PSP33.controller.rules.__implementation;

import it.polimi.ingsw.PSP33.controller.rules.BasicAction;
import it.polimi.ingsw.PSP33.controller.rules.GetCell;
import it.polimi.ingsw.PSP33.controller.rules._move.Move;
import it.polimi.ingsw.PSP33.controller.rules._extraTurn.ExtraAction;
import it.polimi.ingsw.PSP33.controller.rules.buffer_control.DataBuffer;
import it.polimi.ingsw.PSP33.events.toClient.turn.NewAction;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Extra action nad movement with the rule of Prometheus
 *
 */
public class Prometheus implements Move, ExtraAction {

    @Override
    public List<Cell> checkMove(Pawn pawn, Board board) {

        List<Cell> cellList;
        List<Cell> temp = GetCell.getMovableCells(pawn, board);
        Cell current = board.getGrid()[pawn.getCoordX()][pawn.getCoordY()];

        cellList = temp.stream().filter(c -> c.getFloor() <= current.getFloor()).collect(Collectors.toList());

        return cellList;
    }

    @Override
    public void executeMove(Cell newCell, Pawn pawn, Model model) {
        Cell oldCell = model.getBoard().getGrid()[pawn.getCoordX()][pawn.getCoordY()];
        BasicAction.MovePawn(oldCell, newCell, pawn);

        model.notifyObservers(new NewAction(false, true, false));
    }

    @Override
    public List<Cell> checkPlusAction(Pawn pawn, Board board, DataBuffer dataBuffer) {
        return GetCell.getBuildableCells(pawn, board);
    }

    @Override
    public void applyAction(Cell cell, Pawn pawn, Model model) {
        BasicAction.BuildBlock(cell);

        model.notifyObservers(new NewAction(true, false, false));

    }
}
