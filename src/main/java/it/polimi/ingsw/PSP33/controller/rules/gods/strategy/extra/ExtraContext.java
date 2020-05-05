package it.polimi.ingsw.PSP33.controller.rules.gods.strategy.extra;

import it.polimi.ingsw.PSP33.controller.rules.gods.Demeter;
import it.polimi.ingsw.PSP33.controller.rules.gods.Hephaestus;
import it.polimi.ingsw.PSP33.controller.rules.gods.Artemis;
import it.polimi.ingsw.PSP33.controller.rules.gods.Prometheus;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;
import it.polimi.ingsw.PSP33.utils.Coord;
import it.polimi.ingsw.PSP33.utils.enums.Gods;

import java.util.List;

/**
 * Class that manage the extra action context
 */
public class ExtraContext {

    private ExtraAction extraAction;

    public ExtraContext(Gods godName) {

        switch (godName){
            case ARTEMIS: this.extraAction = new Artemis(); break;
            case DEMETER: this.extraAction = new Demeter(); break;
            case HEPHAESTUS: this.extraAction = new Hephaestus(); break;
            case PROMETHEUS: this.extraAction = new Prometheus(); break;
            default:
               this.extraAction = new NoExtra();
        }
    }

    /**
     * Method to check available move based on the god effect
     * @param pawn pawn involved
     * @param board board of the game
     * @return List of Cell object
     */
    public List<Cell> extraRequest(Pawn pawn, Board board){
        return extraAction.checkPlusAction(pawn, board);
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
