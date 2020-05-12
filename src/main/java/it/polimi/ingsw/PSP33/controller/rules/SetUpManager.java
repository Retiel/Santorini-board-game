package it.polimi.ingsw.PSP33.controller.rules;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import it.polimi.ingsw.PSP33.controller.rules.tools.AbstractManager;
import it.polimi.ingsw.PSP33.controller.rules.tools.BasicAction;
import it.polimi.ingsw.PSP33.controller.rules.tools.GetCell;
import it.polimi.ingsw.PSP33.controller.rules.tools.LightConvertion;
import it.polimi.ingsw.PSP33.events.toClient.data.DataBoard;
import it.polimi.ingsw.PSP33.events.toClient.data.DataPlayer;
import it.polimi.ingsw.PSP33.events.toClient.setup.PossiblePlacement;
import it.polimi.ingsw.PSP33.events.toClient.setup.SelectGods;
import it.polimi.ingsw.PSP33.events.toClient.setup.YourGod;
import it.polimi.ingsw.PSP33.model.*;
import it.polimi.ingsw.PSP33.utils.Coord;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Class that manage everything about the set up phase
 */
public class SetUpManager extends AbstractManager {

    private List<God> gods;
    private List<God> Allgods;
    private int pawn;

    public SetUpManager(Model model) {
        super(model);
        this.pawn = 0;
        this.gods = new ArrayList<>();

        try {
            this.Allgods = parseGods();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }

    /**
     * Method to decide the the starting player
     */
    public void SetStartingPlayer() {

        Random random = new Random();
        int numberOfPlayers = getModel().getPlayers().size();
        int randomInteger = random.nextInt(numberOfPlayers);

        getModel().setCurrentPlayer(getModel().getPlayers().get(randomInteger));
        getModel().notifyObservers( new DataBoard( LightConvertion.getLightVersion( getBoard() ) ) );

        for (Player player : getModel().getPlayers()){
            getModel().notifyObservers( new DataPlayer(LightConvertion.getLightVersion(player) ) );
        }
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
    public boolean CheckPawnSetUp(){

        List<Player> playerList =  getModel().getPlayers();

        for (Player player : playerList){
            if (player.getPawns()[0].getCoord() == null) return false;
            if (player.getPawns()[1].getCoord() == null) return false;
        }
        return true;
    }

    /**
     * Method to check if is still the set up phase
     *
     * @return Boolean (true = end set up, false = still need to set up)
     */
    public boolean CheckCardSetUp(){

        List<Player> playerList =  getModel().getPlayers();

        for (Player player : playerList){
            if (player.getCard() == null) return false;
        }
        return true;
    }

    /**
     * Method to set a god for the current player
     */
    public void setGodforPlayer(God god){
        gods.remove(god);
        getModel().getCurrentPlayer().setCard(god);
    }

    /**
     * Method to ask current player to choose his god
     */
    public void playersChooseGod(){
        getModel().notifyObservers(new YourGod(gods));
    }

    /**
     * Method to ask player to choose the gods to use in game
     */
    public void AskGods() {
      getModel().notifyObservers(new SelectGods(Allgods));
    }

    /**
     * Method to ask player where to place the pawn in the setup stage
     */
    public void AskPlayers(){
        getModel().notifyObservers(new PossiblePlacement(GetAvailablePlacement()));
    }

    /**
     * Method to parse the gods name and description
     *
     * @return List of God object
     */
    private List<God> parseGods() throws FileNotFoundException {
        Gson gson = new Gson();

        return gson.fromJson(
                new FileReader("src/main/resources/gods.json"),
                new TypeToken<List<God>>(){}.getType());
    }

    public void setGods(List<God> gods) {
        this.gods = gods;
    }

    /* Methods generated for testing purpose */

    public Model getModel() {
        return super.getModel();
    }

    public int getPawn() {
        return pawn;
    }
}
