package it.polimi.ingsw.PSP33.controller.rules;

import it.polimi.ingsw.PSP33.controller.rules.build.BuildContext;
import it.polimi.ingsw.PSP33.controller.rules.move.MoveContext;
import it.polimi.ingsw.PSP33.controller.rules.turn.ExtraContext;
import it.polimi.ingsw.PSP33.controller.rules.win.WinContext;
import it.polimi.ingsw.PSP33.events.toClient.turn.NewTurn;
import it.polimi.ingsw.PSP33.model.Model;

/**
 * Class that manage everything related to how execute a player turn
 */
public class TurnManager {

    private final Model model;

    private MoveContext moveContext;
    private BuildContext buildContext;
    private WinContext winContext;
    private ExtraContext extraContext;

    public TurnManager(Model model) {
        this.model = model;
    }

    /**
     * Method that starts every players turn, it reset context and change it to the current
     */
    public void NewTurnContext(){

        String god = model.getCurrentPlayer().getCard().getName();

        this.moveContext = new MoveContext(god);
        this.buildContext = new BuildContext(god);
        this.winContext = new WinContext(god);
        this.extraContext = new ExtraContext(god);

        if (god.equals("Prometheus")) model.notifyObservers(new NewTurn(true, true));
        else model.notifyObservers(new NewTurn(true, false));
    }


}
