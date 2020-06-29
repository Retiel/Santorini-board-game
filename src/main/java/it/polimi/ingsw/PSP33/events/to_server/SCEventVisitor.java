package it.polimi.ingsw.PSP33.events.to_server;

import it.polimi.ingsw.PSP33.events.to_server.connection.*;

/**
 * Custom interface used to implement the visitor pattern for setup events sent to the server
 */
public interface SCEventVisitor {

    void visit(ConnectionSelected connectionSelected);

    void visit(NumberOfPlayersSelected numberOfPlayersSelected);

    void visit(LobbySelected lobbySelected);

    void visit(NameSelected nameSelected);

    void visit(ColorSelected colorSelected);
}
