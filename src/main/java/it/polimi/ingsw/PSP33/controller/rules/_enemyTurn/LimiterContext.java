package it.polimi.ingsw.PSP33.controller.rules._enemyTurn;

import it.polimi.ingsw.PSP33.controller.rules.GetCell;
import it.polimi.ingsw.PSP33.controller.rules.__implementation.Athena;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;
import it.polimi.ingsw.PSP33.utils.Coord;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that manage all limit context
 */
public class LimiterContext {

    private Limiter limiter;
    private final List<String> allGods;
    private final List<ActiveGods> allEffect;

    public LimiterContext() {
        this.allGods = new ArrayList<>();
        allGods.add("Athena");
        allGods.add("noLimit");

        this.allEffect =  new ArrayList<>();
        allEffect.add(new ActiveGods("Athena", false));
        allEffect.add(new ActiveGods("noLimit", false));
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
    public List<Cell> applyAllLimit(List<Cell> cellList, Pawn enemyPawn, Board board, String godName){

        List<Cell> afterEffectList = cellList;

        for (ActiveGods gods : allEffect){
            if (godName.equals(gods.getName()) && gods.isEnable()){
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
     * @param c coordinates
     * @param b board
     */
    public void activateGodLimiter(String godName, Pawn p, Coord c, Board b){
        Cell cell = GetCell.getCellAdapter(c, b);
        ChangeLimiter(godName);
        for (ActiveGods gods : allEffect){
            if (godName.equals(gods.getName())) gods.setEnable(limiter.activation(p,cell,b));
        }
    }

    /**
     * Method to reset god effecto to his default state
     * @param name name of the god
     * @param value default value
     */
    public void resetGodTrigger(String name, boolean value){
        for (ActiveGods gods : allEffect){
            if (name.equals(gods.getName())) gods.setEnable(value);
        }
    }

    /**
     * Method change limiter
     * @param godName name of the gopd
     */
    private void ChangeLimiter(String godName){
        switch (godName){
            case "Athena":
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

        private final String name;
        private boolean enable;

        public ActiveGods(String name, boolean enable) {
            this.name = name;
            this.enable = enable;
        }

        public String getName() {
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
