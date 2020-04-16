package it.polimi.ingsw.PSP33.controller.rules._implementation;

import it.polimi.ingsw.PSP33.controller.rules.BasicAction;
import it.polimi.ingsw.PSP33.controller.rules.move.Move;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

/**
 * Movement with the rule of Minotaur
 *
 */
public class Minotaur implements Move {

    @Override
    public List<Cell> checkMove(Pawn pawn, Board board) {

        /* Duplicated code ref. -> MoveApollo*/
        Apollo checkMove = new Apollo();

        return checkMove.checkMove(pawn, board);
    }

    @Override
    public void executeMove(Cell newCell, Pawn pawn, Board board) {

        //TODO: confront with the others for changes
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

        BasicAction.MovePawn(oldCell, newCell, pawn);
        otherCell.setOccupied(otherPawn);
    }
}
