package it.polimi.ingsw.PSP33.view;


import it.polimi.ingsw.PSP33.controller.Controller;
import it.polimi.ingsw.PSP33.events.toServer.VCEventSample;
import it.polimi.ingsw.PSP33.events.toServer.setup.PlacePawn;
import it.polimi.ingsw.PSP33.events.toServer.turn.*;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.God;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Player;
import it.polimi.ingsw.PSP33.utils.Coord;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class testClient {

    public static void main(String[] args) {

        testView view = new testView();

        List<Player> testPlayers = new ArrayList<>();

        Player testPlayer1 = new Player("testPlayer1", Color.BLACK);
        Player testPlayer2 = new Player("testPlayer2", Color.GREEN);

        testPlayers.add(testPlayer1);
        testPlayers.add(testPlayer2);

        Model model = new Model(testPlayers);
        Controller controller = new Controller(model);

        model.addObserver(view);
        view.addObserver(controller);

        view.notifyObservers(new VCEventSample()); /* test to ensure MVC functioning */
        System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        view.notifyObservers(new PlacePawn(new Coord(0,0))); /* test setup branch message */
        System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        modelScenario(model, testPlayer1, testPlayer2);

        view.notifyObservers(new NewTurn()); /* test new turn branch message */
        System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        view.notifyObservers(new RequestPossibleMove(2)); /* test Move branch message */
        view.notifyObservers(new MoveAction(new Coord(2,2)));
        System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        view.notifyObservers(new RequestPossibleBuild(2)); /* test Build branch message */
        view.notifyObservers(new BuildAction(new Coord(2,3), false));
        System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        //changeGod(testPlayer1, testPlayer2);
        //view.notifyObservers(new RequestExtraAction(1)); /* test Extra Action branch message */

        //TODO : test extra action
    }

/*
              NOTE! use this legend as a reference for the diffent test cases

              legend:
              * -> roof = true
              0,...,3 -> floor number
              p -> pawn position player1
                 god: Apollo
              e -> pawn position player2
                 god: Athena

              -> Graphical rappresentation of the board state: (using matrix standard refence with the upper left stating as a [0,0] coordinates)
                         _______ _______ _______ _______ _______
                        |       |       |   p   |       |       |
                        |   0   |   1   |   0   |   0   |   1   |
                        |_______|_______|_______|_______|_______|
                        | *     |       |   *   |       |       |
                        |   0   |   1   |   3   |   1   |   1   |
                        |_______|_______|_______|_______|_______|
                        | e     |       |       | p     |       |
                        |   0   |   3   |   0   |   0   |   2   |
                        |_______|_______|_______|_______|_______|
                        |   *   |       | e     |       |       |
                        |   0   |   2   |   2   |   0   |   2   |
                        |_______|_______|_______|_______|_______|
                        |       |       |       |       |       |
                        |   0   |   1   |   0   |   3   |   0   |
                        |_______|_______|_______|_______|_______|

*/

    private static void modelScenario(Model model, Player testPlayer1,Player testPlayer2){

        model.setCurrentPlayer(testPlayer2);
        testPlayer1.setCard(new God("Apollo", "Eminem - Berzerk (Official Music Video) (Explicit)"));
        testPlayer2.setCard(new God("Athena", "Amazing Trees"));

        Board board = model.getBoard();

        board.getGrid()[0][1].setFloor(1);
        board.getGrid()[0][4].setFloor(1);
        board.getGrid()[1][1].setFloor(1);
        board.getGrid()[1][2].setFloor(3);
        board.getGrid()[1][3].setFloor(1);
        board.getGrid()[1][4].setFloor(1);
        board.getGrid()[2][1].setFloor(3);
        board.getGrid()[2][4].setFloor(2);
        board.getGrid()[3][1].setFloor(2);
        board.getGrid()[3][2].setFloor(1);
        board.getGrid()[3][4].setFloor(2);
        board.getGrid()[4][1].setFloor(1);
        board.getGrid()[4][3].setFloor(3);

        board.getGrid()[1][0].setRoof(true);
        board.getGrid()[1][2].setRoof(true);
        board.getGrid()[3][0].setRoof(true);

        testPlayer1.getPawns()[0].setCoords(0,2);
        board.getGrid()[0][2].setOccupied(testPlayer1.getPawns()[0]);

        testPlayer1.getPawns()[1].setCoords(2,3);
        board.getGrid()[2][3].setOccupied(testPlayer1.getPawns()[1]);

        testPlayer2.getPawns()[0].setCoords(2,0);
        board.getGrid()[2][0].setOccupied(testPlayer2.getPawns()[0]);

        testPlayer2.getPawns()[1].setCoords(3,2);
        board.getGrid()[3][2].setOccupied(testPlayer2.getPawns()[1]);
    }

    private static void changeGod(Player testPlayer1,Player testPlayer2){
        testPlayer1.setCard(new God("Artemis", "Eminem - Berzerk (Official Music Video) (Explicit)"));
        testPlayer2.setCard(new God("Pan", "Amazing Trees"));
    }
}
