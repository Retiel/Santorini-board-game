package it.polimi.ingsw.PSP33.controller.rules.move;

import it.polimi.ingsw.PSP33.controller.rules.Tools;
import it.polimi.ingsw.PSP33.controller.rules.TurnAction;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Movement with the rule of Minotaur
 *
 */
public class MoveMinotaur implements Move {

    @Override
    public List<Cell> checkMove(Pawn pawn, Board board) {

        /* Duplicated code ref. -> MoveApollo*/
        List<Cell> movableCells = Tools.getMovableCells(pawn, board);

        List<Cell> movableByGod = Tools.getAdjacentCells(pawn, board);
        Cell current = board.getGrid()[pawn.getCoordX()][pawn.getCoordY()];
        movableByGod = movableByGod.stream().filter(c -> (current.getFloor() - c.getFloor()) < 2 && !c.isRoof() && !movableCells.contains(c)).collect(Collectors.toList());

        return movableByGod;
    }

    @Override
    public void executeMove(Cell newCell, Pawn pawn, Board board) {

        Cell oldCell = board.getGrid()[pawn.getCoordX()][pawn.getCoordY()];

        int x = newCell.getCoordX();
        int y = newCell.getCoordY();
        int dX = x - pawn.getCoordX();
        int dY = y - pawn.getCoordY();
        int i = 0;

        Pawn otherPawn = newCell.getOccupied();
        Cell otherCell = null;

        do {
            i++;
            if (board.getGrid()[x + dX*i][y + dY*i].getOccupied() == null && !board.getGrid()[x + dX*i][y + dY*i].isRoof()){
                otherCell = board.getGrid()[x + dX*i][y + dY*i];
                break;
            }
        }while (!(dX*(i+1) > 4 || dY*(i+1) > 4 ) );

        TurnAction.MovePawn(oldCell, newCell, pawn);
        otherCell.setOccupied(otherPawn);

    }
}
