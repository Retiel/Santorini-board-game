package it.polimi.ingsw.PSP33.events.to_server;

import it.polimi.ingsw.PSP33.events.to_server.setup.ChoosenGod;
import it.polimi.ingsw.PSP33.events.to_server.setup.PlacePawn;
import it.polimi.ingsw.PSP33.events.to_server.setup.PlayerDisconnected;
import it.polimi.ingsw.PSP33.events.to_server.setup.SelectedGods;
import it.polimi.ingsw.PSP33.events.to_server.turn.*;

/**
 * Custom interface used to implement the visitor pattern for game events sent to the server
 */
public interface VCEventVisitor {

    void visit(ChoosenGod choosenGod);

    void visit(PlacePawn placePawn);

    void visit(PlayerDisconnected playerDisconnected);

    void visit(SelectedGods selectedGods);

    void visit(SelectedPawn selectedPawn);

    void visit(NewTurn newTurn);

    void visit(ExtraAction extraAction);

    void visit(BuildAction buildAction);

    void visit(MoveAction moveAction);

    void visit(RequestExtraAction requestExtraAction);

    void visit(RequestPossibleMove requestPossibleMove);

    void visit(RequestPossibleBuild requestPossibleBuild);
}
