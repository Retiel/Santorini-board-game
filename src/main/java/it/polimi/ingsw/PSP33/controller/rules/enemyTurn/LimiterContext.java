package it.polimi.ingsw.PSP33.controller.rules.enemyTurn;

import it.polimi.ingsw.PSP33.controller.rules._implementation.Athena;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;

import java.util.ArrayList;
import java.util.List;

public class LimiterContext {

    private EnemyLimiter enemyLimiter;
    private List<String> allEnemyEffect;

    public LimiterContext() {
        this.allEnemyEffect = new ArrayList<>();
        allEnemyEffect.add("Athena");
    }

    public List<Cell> applyLimit(List<Cell> cellList, Cell position, Board board, String godName){

        List<Cell> afterEffectList = cellList;

        for (String effect : allEnemyEffect){
            ChangeLimiter(effect);
            afterEffectList = enemyLimiter.Limit(afterEffectList, position, board);
            if(afterEffectList.isEmpty()) break;
        }
        return afterEffectList;
    }

    private void ChangeLimiter(String godName){
        switch (godName){
            case "Athena": enemyLimiter = new Athena();
        }
    }
}
