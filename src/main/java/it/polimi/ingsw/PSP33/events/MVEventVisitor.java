package it.polimi.ingsw.PSP33.events;

import it.polimi.ingsw.PSP33.events.toClient.MVEventSample;
import it.polimi.ingsw.PSP33.events.toClient.setup.SetUpPawn;
import it.polimi.ingsw.PSP33.events.toClient.turn.NewTurn;
import it.polimi.ingsw.PSP33.events.toClient.turn.PossibleBuild;
import it.polimi.ingsw.PSP33.events.toClient.turn.PossibleExtraAction;
import it.polimi.ingsw.PSP33.events.toClient.turn.PossibleMove;

/**
 * Custom interface used to implement the visitor pattern for messages sent to client
 */
public interface MVEventVisitor {

    /* add visit method for each MVEvent */

    void visit(MVEventSample mvEventSample);

    /**
     * Message to request the player where to put their pawns during the set up
     */
    void visit(SetUpPawn setUpPawn);

    /**
     * Message to request the player what to do at the star of his turn
     */
    void visit(NewTurn newTurn);

    /**
     * Message to request the player to select a cell for his move
     */
    void visit(PossibleBuild possibleBuild);

    /**
     * Message to request the player to select a cell where to build
     */
    void visit(PossibleMove possibleMove);

    /**
     * Message to request the player if use the gods power
     */
    void visit(PossibleExtraAction possibleExtraAction);
}
