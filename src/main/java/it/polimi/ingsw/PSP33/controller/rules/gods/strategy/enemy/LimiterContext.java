package it.polimi.ingsw.PSP33.controller.rules.gods.strategy.enemy;

import it.polimi.ingsw.PSP33.controller.rules.tools.GetCell;
import it.polimi.ingsw.PSP33.controller.rules.gods.Athena;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;
import it.polimi.ingsw.PSP33.utils.Coord;
import it.polimi.ingsw.PSP33.utils.enums.Gods;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that manage all limit context
 */
public class LimiterContext {

    private Limiter limiter;
    private final List<ActiveGods> allEffect;

    public LimiterContext() {

        this.allEffect =  new ArrayList<>();
        allEffect.add(new ActiveGods(Gods.ATHENA, false));
        allEffect.add(new ActiveGods(Gods.NOGOD, false));
    }

    /**
     * Method to check available move based on the god effect
     * @param cellList list available action to limit
     * @param enemyPawn pawn involved
     * @param board board of the game
     * @param godName name of the god to exclude
     *
     * @return List of Cell object
     */
    public List<Cell> applyAllLimit(List<Cell> cellList, Pawn enemyPawn, Board board, Gods godName){

        List<Cell> afterEffectList = cellList;

        for (ActiveGods gods : allEffect){
            if (gods.isEnable())
                if (!godName.equals(gods.getName())) {
                    ChangeLimiter(gods.getName());
                    afterEffectList = limiter.limit(afterEffectList, enemyPawn, board);
                }
            if(afterEffectList.isEmpty()) break;
        }
        return afterEffectList;
    }

    /**
     * Method to activate a god effect limit
     * @param godName name of the god to enable
     * @param p pawn involved
     * @param c coordinates needed
     * @param b board
     */
    public void activateGodLimiter(Gods godName, Pawn p, Coord c, Board b){
        Cell cell = GetCell.getCellAdapter(c, b);

        for (ActiveGods gods : allEffect){
            ChangeLimiter(gods.getName());
            if (godName.equals(gods.getName()) && !gods.enable) gods.setEnable(limiter.activation(p,cell,b));
        }
    }

    /**
     * Method to reset god effect to a default state
     * @param name name of the god
     * @param value default state
     */
    public void resetGodTrigger(Gods name, boolean value){
        for (ActiveGods gods : allEffect){
            if (name.equals(gods.getName())) gods.setEnable(value);
        }
    }

    /**
     * Method change limiter
     * @param godName name of the gopd
     */
    private void ChangeLimiter(Gods godName){
        switch (godName){
            case ATHENA:
                limiter = new Athena();
                break;
            default:
                limiter = new NoLimiter();
        }
    }

    /**
     * Private class used to make list with enables
     */
    private static class ActiveGods{

        private final Gods name;
        private boolean enable;

        public ActiveGods(Gods name, boolean enable) {
            this.name = name;
            this.enable = enable;
        }

        public Gods getName() {
            return name;
        }

        public boolean isEnable() {
            return enable;
        }

        public void setEnable(boolean enable) {
            this.enable = enable;
        }
    }
}
