package it.polimi.ingsw.PSP33.controller.rules.__implementation;

import it.polimi.ingsw.PSP33.controller.rules.tools.BasicAction;
import it.polimi.ingsw.PSP33.controller.rules._move.Move;
import it.polimi.ingsw.PSP33.events.toClient.turn.NewAction;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
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
    public void executeMove(Cell newCell, Pawn pawn, Model model) {

        Cell oldCell = model.getBoard().getGrid()[pawn.getCoordX()][pawn.getCoordY()];

        int x = newCell.getCoordX();
        int y = newCell.getCoordY();
        int dX = x - pawn.getCoordX();
        int dY = y - pawn.getCoordY();
        int i = 0;

        Pawn otherPawn = newCell.getOccupied();
        Cell otherCell;

        do {
            i++;
            otherCell = model.getBoard().getGrid()[x + dX*i][y + dY*i];
            if (otherCell.getOccupied() == null && !otherCell.isRoof()) break;

        }while (!(dX*(i+1) > 4 || dY*(i+1) > 4 ) );

        BasicAction.MovePawn(oldCell, newCell, pawn);
        BasicAction.MovePawn(newCell, otherCell, otherPawn);
        newCell.setOccupied(pawn);

        model.notifyObservers(new NewAction(false, true, false));
    }
}
