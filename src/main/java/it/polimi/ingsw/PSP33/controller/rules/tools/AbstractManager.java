package it.polimi.ingsw.PSP33.controller.rules.tools;

import it.polimi.ingsw.PSP33.events.toClient.MVEvent;
import it.polimi.ingsw.PSP33.events.toClient.data.DataCell;
import it.polimi.ingsw.PSP33.model.*;
import it.polimi.ingsw.PSP33.model.light_version.LightModel;
import it.polimi.ingsw.PSP33.utils.Coord;

import java.util.List;

/**
 * Class that manage everything in regards of change player turn and set up turn control
 */
public abstract class AbstractManager {

    private final Model model;

    public AbstractManager(Model model) {
        this.model = model;
    }

    public Model getModel() {
        return model;
    }

    public Board getBoard() {
        return model.getBoard();
    }

    /**
     * Method to move the game to the next player turn
     */
    public void nextTurn() {

        Player current = model.getCurrentPlayer();
        Player nextPlayer;
        int next = model.getPlayers().indexOf(current) + 1;

        if(next < model.getPlayers().size()) nextPlayer = model.getPlayers().get(next);
        else nextPlayer = model.getPlayers().get(0);

        model.setCurrentPlayer(nextPlayer);
        model.setCurrentPawn(null);
    }

    /**
     * Method to set the current pawn
     */
    public void setCurretPawn(int pawn){
        getModel().setCurrentPawn(getModel().getCurrentPlayer().getPawnByNumber(pawn));
    }

    /**
     * Method to remove a player form the game
     */
    public void removePlayer(String name){

        Player player = model.getPlayers().stream().filter(p -> name.equals(p.getName())).findAny().orElse(null);

        List<Player> players = model.getPlayers();
        removePawn(player.getPawns());
        players.remove(player);
        model.setPlayers(players);
    }

    /**
     * Method to remove pawns of the removed player from the grid
     * @param pawns list of the pawns
     */
    private void removePawn(Pawn[] pawns){
        for (Pawn pawn: pawns){
            Coord coord = pawn.getCoord();

            Cell cell = getBoard().getGrid()[coord.getX()][coord.getY()];
            cell.setOccupied(null);
            notifyView(new DataCell(LightConvertion.getLightVersion(cell), null));
        }
    }

    /* method used for testing */
    public void notifyView(MVEvent mvEvent){
        model.notifyObservers(mvEvent);
    }
}
