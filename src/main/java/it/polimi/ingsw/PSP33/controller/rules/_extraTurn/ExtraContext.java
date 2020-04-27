package it.polimi.ingsw.PSP33.controller.rules._extraTurn;

import it.polimi.ingsw.PSP33.controller.rules.__implementation.Demeter;
import it.polimi.ingsw.PSP33.controller.rules.__implementation.Hephaestus;
import it.polimi.ingsw.PSP33.controller.rules.__implementation.Artemis;
import it.polimi.ingsw.PSP33.controller.rules.__implementation.Prometheus;
import it.polimi.ingsw.PSP33.controller.rules.buffer_control.DataBuffer;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;
import it.polimi.ingsw.PSP33.utils.Coord;

import java.util.List;

/**
 * Class that manage the extra action context
 */
public class ExtraContext {

    private ExtraAction extraAction;

    public ExtraContext(String godName) {

        switch (godName){
            case "Artemis": this.extraAction = new Artemis(); break;
            case "Demeter": this.extraAction = new Demeter(); break;
            case "Hephaestus": this.extraAction = new Hephaestus(); break;
            case "Prometheus": this.extraAction = new Prometheus(); break;
            default:
               this.extraAction = new NoExtra();
        }
    }

    /**
     * Method to check available move based on the god effect
     * @param pawn pawn involved
     * @param board board of the game
     * @param dataBuffer data bank for all necessities
     *
     * @return List of Cell object
     */
    public List<Cell> extraRequest(Pawn pawn, Board board, DataBuffer dataBuffer){
        return extraAction.checkPlusAction(pawn, board, dataBuffer);
    }

    /**
     * Method to execute move based on the god effect
     * @param coord coordinates
     * @param pawn pawn involved
     * @param model model used also for notify
     */
    public void ExecAction(Coord coord, Pawn pawn, Model model){
        Cell cell =  model.getBoard().getGrid()[coord.getX()][coord.getY()];
        extraAction.applyAction(cell, pawn, model);
    }
}
