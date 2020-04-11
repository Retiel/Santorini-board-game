package it.polimi.ingsw.PSP33.controller.rules.build;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.List;

public class BuildContext {

    private Build build;

    public BuildContext(String godName) {

        switch (godName){
            case "Atlas": this.build = new BuildBasic();
            case "Demeter": this.build = new BuildBasic();
            case "Hephaestus": this.build = new BuildBasic();
            default:
                this.build = new BuildBasic();
        }
    }

    public List<Cell> checkBuild(Pawn p, Board b){
        return  build.checkBuild(p,b);
    }

    public void execBuild(int coordX, int coordY, Pawn p, Board b){
        build.executeBuild(coordX, coordY, b);
    }
}
