package it.polimi.ingsw.PSP33.controller.rules;

import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

public class SetUpTurn {

    private final Model model;

    public SetUpTurn(Model model) {
        this.model = model;
    }

    /**
     * Method to get the available cell where to place the pawn
     */
    public List<Cell> GetAvailableBoard(){
        return GetCell.getPlaceableCells(model.getBoard());
    }

    /**
     * Method to place the pawn once
     */
    public void PlacePlayerPawn(int coordX, int coordY, int pawn){
        Cell startingCell = model.getBoard().getGrid()[coordX][coordY];
        Pawn pawn1 = model.getCurrentPlayer().getPawns()[pawn];
        BasicAction.SetUpPawnPosition(startingCell, pawn1);
    }
}
