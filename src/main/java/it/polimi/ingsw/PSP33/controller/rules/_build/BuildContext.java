package it.polimi.ingsw.PSP33.controller.rules._build;

import it.polimi.ingsw.PSP33.controller.rules.__implementation.Atlas;
import it.polimi.ingsw.PSP33.controller.rules.__implementation.Demeter;
import it.polimi.ingsw.PSP33.controller.rules.__implementation.Hephaestus;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

/**
 * Class that manage the build context
 */
public class BuildContext {

    private Build build;

    public BuildContext(String godName) {

        switch (godName){
            case "Atlas": this.build = new Atlas(); break;
            case "Demeter": this.build = new Demeter(); break;
            case "Hephaestus": this.build = new Hephaestus(); break;
            default:
                this.build = new BuildBasic();
        }
    }

    /**
     * Method to check available build based on the god effect
     * @param p pawn involved
     * @param b board of the game
     *
     * @return List of Cell object
     */
    public List<Cell> checkBuild(Pawn p, Board b){
        return  build.checkBuild(p,b);
    }

    /**
     * Method to execute move based on the god effect
     * @param coordX coordinate x
     * @param coordY coordinate y
     * @param trigger used for roof
     * @param model model used also for notify
     */
    public void execBuild(int coordX, int coordY, boolean trigger, Model model){
        Cell cellBuild = model.getBoard().getGrid()[coordX][coordY];
        build.executeBuild(cellBuild, trigger, model);
    }
}
