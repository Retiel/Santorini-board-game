package it.polimi.ingsw.PSP33.controller.rules;

import it.polimi.ingsw.PSP33.events.toClient.data.DataModel;
import it.polimi.ingsw.PSP33.events.toClient.setup.PossiblePlacement;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;
import it.polimi.ingsw.PSP33.model.Player;
import it.polimi.ingsw.PSP33.utils.Coord;

import java.util.List;
import java.util.Random;

/**
 * Class that manage everything  about the set up phase
 */
public class SetUpTurn {

    private final Model model;
    private int pawn;

    public SetUpTurn(Model model) {
        this.model = model;
        this.pawn = 0;
    }

    /**
     * Method to decide the the starting player
     */
    public void SetStartingPlayer() {

        Random random = new Random();
        int numberOfPlayers = model.getPlayers().size();
        int randomInteger = random.nextInt(numberOfPlayers);

        model.setCurrentPlayer(model.getPlayers().get(randomInteger));
        model.notifyObservers(new DataModel(model));
    }

    /**
     * Method to get list of possible cell to place the pawn during set up
     *
     * @return List of Coord class object
     */
    public List<Coord> GetAvailablePlacement(){
        List<Cell> cellList = GetCell.getPlaceableCells(model.getBoard());
        return GetCell.getListAdapter(cellList);
    }

    /**
     * Method to place the pawn once
     * @param coordX coordinate x of the cell
     * @param coordY coordinate y of the cell
     */
    public void PlacePlayerPawn(int coordX, int coordY){
        Cell startingCell = model.getBoard().getGrid()[coordX][coordY];
        Pawn pawn1 = model.getCurrentPlayer().getPawns()[pawn];
        BasicAction.SetUpPawnPosition(startingCell, pawn1);
        if (pawn == 0) pawn++;
        else pawn = 0;
    }

    /**
     * Method to check if is still the current player turn
     *
     * @return Boolean (true = end current player turn, false = still current player turn)
     */
    public boolean CheckEndTurn(){
        return pawn < 1;
    }

    /**
     * Method to check if is still the set up phase
     *
     * @return Boolean (true = end set up, false = still need to set up)
     */
    public boolean CheckEndSetUp(){

        List<Player> playerList =  model.getPlayers();

        for (Player player : playerList){
            if (player.getPawns()[0].getCoord() == null) return false;
            if (player.getPawns()[1].getCoord() == null) return false;
        }
        return true;
    }

    public void AskPlayers(){
        model.notifyObservers(new PossiblePlacement(GetAvailablePlacement()));
    }


    /* Methods generated for testing purpose */

    public Model getModel() {
        return model;
    }

    public int getPawn() {
        return pawn;
    }
}
