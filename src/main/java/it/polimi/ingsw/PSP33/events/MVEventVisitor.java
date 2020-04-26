package it.polimi.ingsw.PSP33.events;

import it.polimi.ingsw.PSP33.events.toClient.MVEventSample;
import it.polimi.ingsw.PSP33.events.toClient.data.DataModel;
import it.polimi.ingsw.PSP33.events.toClient.setup.PossiblePlacement;
import it.polimi.ingsw.PSP33.events.toClient.turn.*;

/**
 * Custom interface used to implement the visitor pattern for messages sent to client
 */
public interface MVEventVisitor {

    /* add visit method for each MVEvent */

    void visit(MVEventSample mvEventSample);

    /**
     * Message to send to the player all inital data of the board
     */
    void visit(DataModel dataModel);
    /**
     * Message to send to the player all inital data of the board
     */
    void visit(PossiblePlacement possiblePlacement);

    /**
     * Message to comunicate to the losing statement
     */
    void visit(YouLose youLose);

    /**
     * Message to request the player what to do at the star of his turn
     */
    void visit(NewAction newAction);

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
