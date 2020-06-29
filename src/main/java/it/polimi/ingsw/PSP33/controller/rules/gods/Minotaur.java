package it.polimi.ingsw.PSP33.controller.rules.gods;

import it.polimi.ingsw.PSP33.controller.rules.tools.BasicAction;
import it.polimi.ingsw.PSP33.controller.rules.gods.strategy.move.Move;
import it.polimi.ingsw.PSP33.controller.rules.tools.GetCell;
import it.polimi.ingsw.PSP33.controller.rules.tools.LightConversion;
import it.polimi.ingsw.PSP33.events.to_client.data.DataCell;
import it.polimi.ingsw.PSP33.events.to_client.turn.NewAction;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;
import it.polimi.ingsw.PSP33.model.light_version.LightCell;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Movement with the rule of Minotaur
 *
 */
public class Minotaur implements Move {

    @Override
    public List<Cell> checkMove(Pawn pawn, Board board) {

        List<Cell> movableCells = GetCell.getMovableCells(pawn, board);
        List<Cell> adjacentCells = GetCell.getAdjacentCells(pawn, board);
        List<Cell> movableByGod, pre_movableByGod;

        Cell current = board.getGrid()[pawn.getCoordX()][pawn.getCoordY()];

        pre_movableByGod = adjacentCells.stream().filter(c -> (c.getFloor() - current.getFloor()) < 2 && !movableCells.contains(c) && !c.isRoof()).collect(Collectors.toList());
        movableByGod = new ArrayList<>(pre_movableByGod);

        for (Cell cell : pre_movableByGod){

            int x = cell.getCoordX();
            int y = cell.getCoordY();
            int dX = x - pawn.getCoordX();
            int dY = y - pawn.getCoordY();

            int i = 0;

            do {
                Cell otherCell = board.getGrid()[x + dX*i][y + dY*i];
                if (otherCell.getOccupied() == null && !otherCell.isRoof()) break;

                if ((x + dX*(i+1)) > 4) {
                    movableByGod.remove(cell);
                    break;
                }
                if ((y + dY*(i+1)) > 4) {
                    movableByGod.remove(cell);
                    break;
                }
                if ((x + dX*(i+1)) < 0) {
                    movableByGod.remove(cell);
                    break;
                }
                if ((y + dY*(i+1)) < 0) {
                    movableByGod.remove(cell);
                    break;
                }
                i++;
            }while (true);

        }

        return movableByGod;
    }

    @Override
    public void executeMove(Cell newCell, Pawn pawn, Model model) {

        Cell oldCell = model.getBoard().getGrid()[pawn.getCoordX()][pawn.getCoordY()];

        Pawn otherPawn = newCell.getOccupied();


        if (otherPawn != null){
            int x = newCell.getCoordX();
            int y = newCell.getCoordY();
            int dX = x - pawn.getCoordX();
            int dY = y - pawn.getCoordY();
            int i = 0;

            Cell otherCell;

            do {
                i++;
                otherCell = model.getBoard().getGrid()[x + dX*i][y + dY*i];
                if (otherCell.getOccupied() == null && !otherCell.isRoof()) break;

                if (!( (x + dX*(i+1)) > 4 || (y + dY*(i+1)) > 4 )) break;
                if (!( (x + dX*(i+1)) < 0 || (y + dY*(i+1)) < 0 )) break;
            }while (true);

            BasicAction.movePawn(oldCell, newCell, pawn);
            BasicAction.movePawn(newCell, otherCell, otherPawn);
            newCell.setOccupied(pawn);

            LightCell lightOtherCellNew = LightConversion.getLightVersion(otherCell);
            model.notifyObservers(new DataCell(lightOtherCellNew, null));
        } else{
            BasicAction.movePawn(oldCell, newCell, pawn);
        }

        LightCell lightCellOld = LightConversion.getLightVersion(oldCell);
        LightCell lightCellNew = LightConversion.getLightVersion(newCell);

        model.notifyObservers(new DataCell(lightCellNew, lightCellOld));
        model.notifyObservers(new NewAction(false, true, false, false));
    }
}
