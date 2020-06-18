package it.polimi.ingsw.PSP33.controller.rules.gods;

import it.polimi.ingsw.PSP33.controller.rules.gods.strategy.extra.ExtraAction;
import it.polimi.ingsw.PSP33.controller.rules.tools.BasicAction;
import it.polimi.ingsw.PSP33.controller.rules.tools.GetCell;
import it.polimi.ingsw.PSP33.controller.rules.gods.strategy.move.Move;
import it.polimi.ingsw.PSP33.controller.rules.tools.LightConversion;
import it.polimi.ingsw.PSP33.events.toClient.data.DataCell;
import it.polimi.ingsw.PSP33.events.toClient.turn.NewAction;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;
import it.polimi.ingsw.PSP33.model.light_version.LightCell;

import java.util.ArrayList;
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

        if (pawn.getOldExtra() != null) return cellList;
        else return new ArrayList<>();
    }

    @Override
    public void executeMove(Cell newCell, Pawn pawn, Model model) {
        Cell oldCell = model.getBoard().getGrid()[pawn.getCoordX()][pawn.getCoordY()];
        BasicAction.movePawn(oldCell, newCell, pawn);

        LightCell lightCellOld = LightConversion.getLightVersion(oldCell);
        LightCell lightCellNew = LightConversion.getLightVersion(newCell);

        model.notifyObservers(new DataCell(lightCellNew, lightCellOld));
        model.notifyObservers(new NewAction(false, true, false, false));
    }

    @Override
    public List<Cell> checkPlusAction(Pawn pawn, Board board) {
        return GetCell.getBuildableCells(pawn, board);
    }

    @Override
    public void applyAction(Cell cell, Pawn pawn, Model model) {
        BasicAction.buildBlock(cell);
        pawn.setOldExtra(cell.getCoord());

        LightCell lightCellNew = LightConversion.getLightVersion(cell);

        model.notifyObservers(new DataCell(lightCellNew, null));
        model.notifyObservers(new NewAction(true, false, false, false));

    }
}
