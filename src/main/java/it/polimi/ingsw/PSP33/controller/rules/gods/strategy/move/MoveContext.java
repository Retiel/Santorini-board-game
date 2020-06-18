package it.polimi.ingsw.PSP33.controller.rules.gods.strategy.move;

import it.polimi.ingsw.PSP33.controller.rules.gods.Apollo;
import it.polimi.ingsw.PSP33.controller.rules.gods.Artemis;
import it.polimi.ingsw.PSP33.controller.rules.gods.Minotaur;
import it.polimi.ingsw.PSP33.controller.rules.gods.Prometheus;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;
import it.polimi.ingsw.PSP33.utils.enums.Gods;

import java.util.List;

/**
 * Class that manage the move context
 */
public class MoveContext {

    private Move move;

    public MoveContext(Gods godName) {
        switch (godName){
            case APOLLO: this.move = new Apollo(); break;
            case ARTEMIS: this.move = new Artemis(); break;
            case MINOTAUR: this.move = new Minotaur(); break;
            case PROMETHEUS: this.move = new Prometheus(); break;
            default:
                this.move = new MoveBasic();
        }

    }

    /**
     * Method to check available move based on the god effect
     * @param pawn pawn involved
     * @param board board of the game
     *
     * @return List of Cell object
     */
    public List<Cell> checkMove(Pawn pawn, Board board){
        return  move.checkMove(pawn, board);
    }

    /**
     * Method to execute move based on the god effect
     * @param coordX coordinate x
     * @param coordY coordinate y
     * @param pawn pawn involved
     * @param model model used also for notify
     */
    public void execMove(int coordX, int coordY, Pawn pawn, Model model){
        Cell newCell = model.getBoard().getGrid()[coordX][coordY];
        move.executeMove(newCell, pawn, model);
    }

}
