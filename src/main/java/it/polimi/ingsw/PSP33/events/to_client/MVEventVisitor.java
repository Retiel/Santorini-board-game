package it.polimi.ingsw.PSP33.events.to_client;

import it.polimi.ingsw.PSP33.events.to_client.data.DataBoard;
import it.polimi.ingsw.PSP33.events.to_client.data.DataCell;
import it.polimi.ingsw.PSP33.events.to_client.data.DataPlayer;
import it.polimi.ingsw.PSP33.events.to_client.setup.CurrentPlayer;
import it.polimi.ingsw.PSP33.events.to_client.setup.PossiblePlacement;
import it.polimi.ingsw.PSP33.events.to_client.setup.SelectGods;
import it.polimi.ingsw.PSP33.events.to_client.setup.YourGod;
import it.polimi.ingsw.PSP33.events.to_client.turn.*;

/**
 * Custom interface used to implement the visitor pattern for game events sent to the client
 */
public interface MVEventVisitor {

    void visit(DataBoard dataBoard);

    void visit(DataCell dataCell);

    void visit(DataPlayer dataPlayer);

    void visit(YourGod yourGod);

    void visit(CurrentPlayer currentPlayer);

    void visit(PossiblePlacement possiblePlacement);

    void visit(SelectGods selectGods);

    void visit(YouLose youLose);

    void visit(YouWin youWin);

    void visit(SelectPawn selectPawn);

    void visit(NewAction newAction);

    void visit(PossibleBuild possibleBuild);

    void visit(PossibleMove possibleMove);

    void visit(PossibleExtraAction possibleExtraAction);
}
