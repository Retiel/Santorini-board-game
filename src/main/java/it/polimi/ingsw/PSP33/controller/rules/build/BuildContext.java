package it.polimi.ingsw.PSP33.controller.rules.build;

import it.polimi.ingsw.PSP33.controller.rules._implementation.Atlas;
import it.polimi.ingsw.PSP33.controller.rules._implementation.Demeter;
import it.polimi.ingsw.PSP33.controller.rules._implementation.Hephaestus;
import it.polimi.ingsw.PSP33.controller.rules._implementation.Prometheus;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

public class BuildContext {

    private Build build;

    public BuildContext(String godName) {

        switch (godName){
            case "Atlas": this.build = new Atlas();
            case "Demeter": this.build = new Demeter();
            case "Hephaestus": this.build = new Hephaestus();
            case "Prometheus": this.build = new Prometheus();
            default:
                this.build = new BuildBasic();
        }
    }

    public List<Cell> checkBuild(Pawn p, Board b){
        return  build.checkBuild(p,b);
    }

    public void execBuild(int coordX, int coordY, Board b, boolean trigger){
        Cell cellBuild = b.getGrid()[coordX][coordY];
        build.executeBuild(cellBuild, trigger);
    }
}
