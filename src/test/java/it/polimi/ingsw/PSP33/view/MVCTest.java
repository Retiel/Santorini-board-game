package it.polimi.ingsw.PSP33.view;


import it.polimi.ingsw.PSP33.controller.Controller;
import it.polimi.ingsw.PSP33.events.toServer.setup.ChoosenGod;
import it.polimi.ingsw.PSP33.events.toServer.setup.PlacePawn;
import it.polimi.ingsw.PSP33.events.toServer.turn.*;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.God;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Player;
import it.polimi.ingsw.PSP33.utils.Coord;
import it.polimi.ingsw.PSP33.utils.enums.Color;
import it.polimi.ingsw.PSP33.utils.enums.Gods;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

public class MVCTest {

    private ViewSample view;
    private Model model;
    private Controller controller;
    private List<God> gods;

    private Player testPlayer1;
    private Player testPlayer2;

    @Before
    public void setUp(){

        view = new ViewSample();

        List<Player> testPlayers = new ArrayList<>();

        testPlayer1 = new Player("testPlayer1", Color.BLUE);
        testPlayer2 = new Player("testPlayer2", Color.GREEN);

        testPlayers.add(testPlayer1);
        testPlayers.add(testPlayer2);

        gods = new ArrayList<>();
        gods.add(new God(Gods.ATHENA, "YOLO"));
        gods.add(new God(Gods.ARTEMIS, "fnihsbgvse"));
        gods.add(new God(Gods.APOLLO, "ewarFHJABFE"));

        model = new Model(testPlayers);
        controller = new Controller(model);

        model.addObserver(view);
        view.addObserver(controller);

    }

    @Test
    public void coreExecution(){
        controller.startGame();

        System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        view.notifyObservers(new PlacePawn(new Coord(1,1))); /* test setup pawn branch message */
        System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        setPawns(model, testPlayer1, testPlayer2);
        view.notifyObservers(new PlacePawn(new Coord(1,1))); /* test setup gods branch message */
        System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        view.notifyObservers(new ChoosenGod(gods.get(1))); /* test setup one god branch message */
        System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        modelScenario(model, testPlayer1, testPlayer2);

        view.notifyObservers(new NewTurn()); /* test new turn branch message */
        view.notifyObservers(new SelectedPawn(1));
        System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        view.notifyObservers(new RequestPossibleMove()); /* test Move branch message */
        view.notifyObservers(new MoveAction(new Coord(0,1)));
        System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        view.notifyObservers(new RequestPossibleBuild()); /* test Build branch message */
        view.notifyObservers(new BuildAction(new Coord(0,0), false));
        System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");
        view.notifyObservers(new RequestExtraAction()); /* test Extra Action branch message */
        view.notifyObservers(new ExtraAction(new Coord(1,1)));
        System.out.print("----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------\n");

    }

/*
              NOTE! use this legend as a reference for the diffent test cases

              legend:
              * -> roof = true
              0,...,3 -> floor number
              p -> pawn position player1
                 god: Artemis
              e -> pawn position player2
                 god: Athena

              -> Graphical rappresentation of the board state: (using matrix standard refence with the upper left stating as a [0,0] coordinates)
                         _______ _______ _______ _______ _______
                        |       |       |   p   |       |       |
                        |   0   |   1   |   0   |   0   |   1   |
                        |_______|_______|_______|_______|_______|
                        | *     |       |   *   |       |       |
                        |   0   |   2   |   3   |   1   |   1   |
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
        testPlayer1.setCard(new God(Gods.ARTEMIS, "Eminem - Berzerk (Official Music Video) (Explicit)"));
        testPlayer2.setCard(new God(Gods.ATHENA, "Amazing Trees"));

        Board board = model.getBoard();

        board.getGrid()[0][0].setOccupied(null); /* reset setup modification */

        /* Config data below and adjust scenarios based on the need and the cases, use the map up as a refence to teh state of the board */
        board.getGrid()[0][1].setFloor(1);
        board.getGrid()[0][4].setFloor(1);
        board.getGrid()[1][1].setFloor(2);
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

    private static void setPawns(Model model, Player testPlayer1,Player testPlayer2){

        Board board = model.getBoard();

        testPlayer1.getPawns()[0].setCoords(0,0);
        testPlayer1.getPawns()[1].setCoords(0,0);

        testPlayer2.getPawns()[0].setCoords(0,0);
        testPlayer2.getPawns()[1].setCoords(0,0);
    }

    private static void changeGod(Player testPlayer1,Player testPlayer2){
        testPlayer1.setCard(new God(Gods.APOLLO, "Eminem - Berzerk (Official Music Video) (Explicit)"));
        testPlayer2.setCard(new God(Gods.PAN, "Amazing Trees"));
    }
}
