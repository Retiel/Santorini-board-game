package it.polimi.ingsw.PSP33.controller.rules._build;

import it.polimi.ingsw.PSP33.controller.rules.__implementation.Atlas;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

public class BuildContext {

    private Build build;

    public BuildContext(String godName) {

        switch (godName){
            case "Atlas": this.build = new Atlas(); break;
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
