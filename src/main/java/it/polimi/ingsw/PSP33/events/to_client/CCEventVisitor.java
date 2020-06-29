package it.polimi.ingsw.PSP33.events.to_client;

import it.polimi.ingsw.PSP33.events.to_client.connection.*;

/**
 * Custom interface used to implement the visitor pattern for setup events sent to the client
 */
public interface CCEventVisitor {

    /**
     *
     * @param selectConnection
     */
    void visit(SelectConnection selectConnection);

    void visit(SelectNumberOfPlayers selectNumberOfPlayers);

    void visit(SelectLobby selectLobby);

    void visit(SelectName selectName);

    void visit(SelectColor selectColor);

    void visit(RequestWait requestWait);

    void visit(AllPlayersReady allPlayersReady);
}
