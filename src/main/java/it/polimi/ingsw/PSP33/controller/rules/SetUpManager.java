package it.polimi.ingsw.PSP33.controller.rules;

import it.polimi.ingsw.PSP33.controller.rules.tools.AbstractManager;
import it.polimi.ingsw.PSP33.controller.rules.tools.BasicAction;
import it.polimi.ingsw.PSP33.controller.rules.tools.GetCell;
import it.polimi.ingsw.PSP33.controller.rules.tools.LightConvertion;
import it.polimi.ingsw.PSP33.events.toClient.data.DataGrid;
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
public class SetUpManager extends AbstractManager {

    private int pawn;

    public SetUpManager(Model model) {
        super(model);
        this.pawn = 0;
    }

    /**
     * Method to decide the the starting player
     */
    public void SetStartingPlayer() {

        Random random = new Random();
        int numberOfPlayers = getModel().getPlayers().size();
        int randomInteger = random.nextInt(numberOfPlayers);

        getModel().setCurrentPlayer(getModel().getPlayers().get(randomInteger));
        getModel().notifyObservers( new DataGrid( LightConvertion.getLightVersion( getBoard() ) ) );
    }

    /**
     * Method to get list of possible cell to place the pawn during set up
     *
     * @return List of Coord class object
     */
    public List<Coord> GetAvailablePlacement(){
        List<Cell> cellList = GetCell.getPlaceableCells(getModel().getBoard());
        return GetCell.getListAdapter(cellList);
    }

    /**
     * Method to place the pawn once
     * @param coordX coordinate x of the cell
     * @param coordY coordinate y of the cell
     */
    public void PlacePlayerPawn(int coordX, int coordY){
        Cell startingCell = getBoard().getGrid()[coordX][coordY];
        Pawn pawn1 = getModel().getCurrentPlayer().getPawns()[pawn];
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

        List<Player> playerList =  getModel().getPlayers();

        for (Player player : playerList){
            if (player.getPawns()[0].getCoord() == null) return false;
            if (player.getPawns()[1].getCoord() == null) return false;
        }
        return true;
    }

    public void AskPlayers(){
        getModel().notifyObservers(new PossiblePlacement(GetAvailablePlacement()));
    }


    /* Methods generated for testing purpose */

    public Model getModel() {
        return super.getModel();
    }

    public int getPawn() {
        return pawn;
    }
}
