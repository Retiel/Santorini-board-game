package it.polimi.ingsw.PSP33.events;

import it.polimi.ingsw.PSP33.events.toServer.setup.PlacePawn;
import it.polimi.ingsw.PSP33.events.toServer.turn.*;

/**
 * Custom interface used to implement the visitor pattern for messages sent to server
 */
public interface VCEventVisitor {

    /**
     * Message to send the server where to place the pawn diuring set up phase
     */
    void visit(PlacePawn placePawn);

    /**
     * Message to send the server to set a new turn
     */
    void visit(NewTurn newTurn);

    /**
     * Message to send the server where to place the pawn diuring set up phase
     */
    void visit(ExtraAction extraAction);

    /**
     * Message to send the server where to build
     */
    void visit(BuildAction buildAction);

    /**
     * Message to send the server where to move the pawn
     */
    void visit(MoveAction moveAction);

    /**
     * Message to send the server to activate their gods power
     */
    void visit(RequestExtraAction requestExtraAction);

    /**
     * Message to send the server to request the possible move
     */
    void visit(RequestPossibleMove requestPossibleMove);

    /**
     * Message to send the sever to request the possible build
     */
    void visit(RequestPossibleBuild requestPossibleBuild);
}
