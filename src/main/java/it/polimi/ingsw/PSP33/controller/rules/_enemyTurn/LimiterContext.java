package it.polimi.ingsw.PSP33.controller.rules._enemyTurn;

import it.polimi.ingsw.PSP33.controller.rules.__implementation.Athena;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.ArrayList;
import java.util.List;

public class LimiterContext {

    private Limiter limiter;
    private List<String> allEnemyEffect;

    public LimiterContext() {
        this.allEnemyEffect = new ArrayList<>();
        allEnemyEffect.add("Athena");
    }

    public List<Cell> applyAllLimit(List<Cell> cellList, Pawn pawn, Board board, String godName){

        List<Cell> afterEffectList = cellList;

        for (String effect : allEnemyEffect){
            if (!godName.equals(effect)){
                ChangeLimiter(effect);
                afterEffectList = limiter.Limit(afterEffectList, pawn, board);
            }
            if(afterEffectList.isEmpty()) break;
        }
        return afterEffectList;
    }

    private void ChangeLimiter(String godName){
        switch (godName){
            case "Athena": limiter = new Athena(); break;
        }
    }
}
