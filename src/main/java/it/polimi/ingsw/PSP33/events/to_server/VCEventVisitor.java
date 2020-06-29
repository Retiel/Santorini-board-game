package it.polimi.ingsw.PSP33.events.to_server;

import it.polimi.ingsw.PSP33.events.to_server.setup.ChoosenGod;
import it.polimi.ingsw.PSP33.events.to_server.setup.SelectedGods;
import it.polimi.ingsw.PSP33.events.to_server.setup.PlacePawn;
import it.polimi.ingsw.PSP33.events.to_server.setup.PlayerDisconnected;
import it.polimi.ingsw.PSP33.events.to_server.turn.*;

/**
 * Custom interface used to implement the visitor pattern for game events sent to the server
 */
public interface VCEventVisitor {

    /**
     * Message to send the server the god a player choose
     */
    void visit(ChoosenGod choosenGod);

    /**
     * Message to send the server where to place the pawn diuring set up phase
     */
    void visit(PlacePawn placePawn);

    /**
     * Message to send the server when a player disconnect form the game
     */
    void visit(PlayerDisconnected playerDisconnected);

    /**
     * Message to send the server the gods choosen during setup
     */
    void visit(SelectedGods selectedGods);

    /**
     * Message to send the server the pawn choosen for the actions
     */
    void visit(SelectedPawn selectedPawn);

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
