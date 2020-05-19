package it.polimi.ingsw.PSP33.events;

import it.polimi.ingsw.PSP33.events.toClient.data.DataCell;
import it.polimi.ingsw.PSP33.events.toClient.data.DataBoard;
import it.polimi.ingsw.PSP33.events.toClient.data.DataPawn;
import it.polimi.ingsw.PSP33.events.toClient.data.DataPlayer;
import it.polimi.ingsw.PSP33.events.toClient.setup.CurrentPlayer;
import it.polimi.ingsw.PSP33.events.toClient.setup.PossiblePlacement;
import it.polimi.ingsw.PSP33.events.toClient.setup.SelectGods;
import it.polimi.ingsw.PSP33.events.toClient.setup.YourGod;
import it.polimi.ingsw.PSP33.events.toClient.turn.*;

/**
 * Custom interface used to implement the visitor pattern for messages sent to client
 */
public interface MVEventVisitor {

    /**
     * Message to send to the player all inital data of the board
     */
    void visit(DataBoard dataBoard);

    /**
     * Message to send to the player all inital data of the modified cell
     */
    void visit(DataCell dataCell);

    /**
     * Message to send to the player all inital data of the player
     */
    void visit(DataPlayer dataPlayer);

    /**
     * Message to send to the player all inital data of the pawn
     */
    void visit(DataPawn dataPawn);

    /**
     * Message to send to the player all inital data of the pawn
     */
    void visit(YourGod yourGod);

    /**
     * Message to send to the player all inital data of the pawn
     */
    void visit(CurrentPlayer currentPlayer);

    /**
     * Message to send to the player all inital data of the board
     */
    void visit(PossiblePlacement possiblePlacement);

    /**
     * Message to send to the player select the N gods to use in the game
     */
    void visit(SelectGods selectGods);

    /**
     * Message to comunicate to the losing statement
     */
    void visit(YouLose youLose);

    /**
     * Message to comunicate to the winning statement
     */
    void visit(YouWin youWin);
    /**
     * Message to request the player what pawn the player is going to use
     */
    void visit(SelectPawn selectPawn);

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
