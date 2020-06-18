package it.polimi.ingsw.PSP33.utils;

import it.polimi.ingsw.PSP33.controller.Controller;
import it.polimi.ingsw.PSP33.controller.rules.tools.BasicAction;
import it.polimi.ingsw.PSP33.controller.rules.tools.LightConversion;
import it.polimi.ingsw.PSP33.events.toClient.data.DataBoard;
import it.polimi.ingsw.PSP33.events.toClient.data.DataCell;
import it.polimi.ingsw.PSP33.events.toClient.turn.NewAction;
import it.polimi.ingsw.PSP33.events.toServer.turn.RequestPossibleMove;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Model;
import it.polimi.ingsw.PSP33.model.Pawn;

import java.util.Scanner;

/* Setting cheat method for debugging purpose */
public class CustomDebbuger implements Runnable{

    private final int lobbyID;

    private final Model model;
    private final Controller controller;

    private final Scanner scanner;

    public CustomDebbuger(int lobbyID, Model model, Controller controller) {
        this.lobbyID = lobbyID;
        this.model = model;
        this.controller = controller;
        this.scanner = new Scanner(System.in);
    }

    @Override
    public void run() {
        while(true){
            String input1 = scanner.nextLine();
            switch (input1){
                case "test":
                    System.out.println("Test, it works");
                    break;
                case "config":
                    /* Note use this mode before the move action */
                    /* Manually config the number to change the configuration  */
                    System.out.println("mode available [1,2]");
                    configModel(readInt(2));
                    controller.visit(new RequestPossibleMove());
                    break;
                case "move":        /* Note: move only in empty cell (switch position is not supported) */
                    System.out.println("select pawn");
                    moveSwitch();
                    break;
                case "build":        /* Note: move only in empty cell (switch position is not supported) */
                    System.out.println("select cell");
                    buildSwitch();
                    break;
                case "turn":
                    System.out.println("select command");
                    turnSwitch();
                    break;
                case "god":
                    System.out.println("select god");
                    godSwitch();
                    break;
                case "info":
                    System.out.println("Lobby number: " + this.lobbyID);
                    break;
                default:
            }
            System.out.println("executing command");
            scanner.reset();
        }
    }

    private void moveSwitch(){

        Cell newCell, oldCell;
        int input = readInt(2);
        switch (input){
            case 1:
                newCell = retrieveNewCell();
                oldCell = retrieveOldCell(1);
                movePawn(1, newCell, oldCell);
                break;
            case 2:
                newCell = retrieveNewCell();
                oldCell = retrieveOldCell(2);
                movePawn(2, newCell, oldCell);
                break;
            default:
                System.out.println("<- reverting instruction");
        }

        System.out.println("Done");
    }

    private void buildSwitch(){
        Cell cell = retrieveNewCell();

        System.out.println("indicate floor level [1,2,3]");
        int floor = readInt(3);
        cell.setFloor(floor);

        model.notifyObservers(new DataCell(LightConversion.getLightVersion(cell), null));
        System.out.println("cell built");
    }

    private void turnSwitch(){
        String command = scanner.nextLine();
        switch (command){
            case "next": /* needs testing */
                model.notifyObservers(new NewAction(false, false, false, false));
                break;
            case "restart": /* Command still impossible due to project limitation */
                //controller.getTurnManager().newTurnContext();
                break;
            default:
        }
    }

    private void godSwitch(){
        /* has to change the god of the next turn */
    }

    private Cell retrieveNewCell(){
        System.out.println("write coordinates 1 by 1");
        int X = readInt(4);
        int Y = readInt(4);
        return model.getBoard().getGrid()[X][Y];
    }

    private Cell retrieveOldCell(int nPawn){
        Pawn pawn = model.getCurrentPlayer().getPawnByNumber(nPawn);
        return model.getBoard().getGrid()[pawn.getCoordX()][pawn.getCoordY()];
    }

    private void movePawn(int pawn, Cell newCell, Cell oldCell){
        BasicAction.movePawn(oldCell, newCell, model.getCurrentPlayer().getPawnByNumber(pawn));
        model.notifyObservers(new DataCell(LightConversion.getLightVersion(newCell), LightConversion.getLightVersion(oldCell)));
    }

    private void configModel(int number) {

        Board board = model.getBoard();

        int[] row0;
        int[] row1;
        int[] row2;
        int[] row3;
        int[] row4;

        int[][] grid;

        boolean[] row0_r;
        boolean[] row1_r;
        boolean[] row2_r;
        boolean[] row3_r;
        boolean[] row4_r;

        boolean[][] grid_r;
        
        switch (number){
            case 1:
                /*
    legend:
        * -> roof = true
        0,...,3 -> floor number

    -> Graphical representation of the board state: (using matrix standard reference with the upper left stating as a [0,0] coordinates)
          _______ _______ _______ _______ _______
         |       |       |       |       |       |
         |   0   |   1   |   0   |   0   |   1   |
         |_______|_______|_______|_______|_______|
         |   *   |       |   *   |       |       |
         |   0   |   2   |   3   |   1   |   1   |
         |_______|_______|_______|_______|_______|
         |       |       |       |       |       |
         |   0   |   3   |   0   |   0   |   2   |
         |_______|_______|_______|_______|_______|
         |   *   |       |       |       |       |
         |   0   |   2   |   2   |   0   |   2   |
         |_______|_______|_______|_______|_______|
         |       |       |       |       |       |
         |   0   |   1   |   0   |   3   |   0   |
         |_______|_______|_______|_______|_______|

*/
                row0 = new int[]{0, 1, 0, 0, 1};
                row1 = new int[]{0, 2, 3, 1, 1};
                row2 = new int[]{0, 3, 0, 0, 2};
                row3 = new int[]{0, 2, 2, 0, 2};
                row4 = new int[]{0, 1, 0, 3, 0};

                grid = new int[][]{row0, row1, row2, row3, row4};

                row0_r = new boolean[]{false, false, false, false, false};
                row1_r = new boolean[]{true, false, true, false, false};
                row2_r = new boolean[]{false, false, false, false, false};
                row3_r = new boolean[]{true, false, false, false, false};
                row4_r = new boolean[]{false, false, false, false, false};

                grid_r = new boolean[][]{row0_r, row1_r, row2_r, row3_r, row4_r};

                configGrid(board, grid, grid_r);
                break;
            case 2:
                /*
    legend:
        * -> roof = true
        0,...,3 -> floor number

    -> Graphical representation of the board state: (using matrix standard reference with the upper left stating as a [0,0] coordinates)
          _______ _______ _______ _______ _______
         |       |       |       |       |       |
         |   3   |   3   |   2   |   0   |   1   |
         |_______|_______|_______|_______|_______|
         |   *   |       |   *   |       |       |
         |   1   |   0   |   3   |   1   |   1   |
         |_______|_______|_______|_______|_______|
         |       |       |       |       |       |
         |   1   |   3   |   0   |   0   |   2   |
         |_______|_______|_______|_______|_______|
         |   *   |       |       |   *   |       |
         |   3   |   2   |   2   |   3   |   2   |
         |_______|_______|_______|_______|_______|
         |       |       |       |       |       |
         |   0   |   3   |   0   |   3   |   0   |
         |_______|_______|_______|_______|_______|

*/

                row0 = new int[]{3, 3, 2, 0, 1};
                row1 = new int[]{1, 0, 3, 1, 1};
                row2 = new int[]{1, 3, 0, 0, 2};
                row3 = new int[]{3, 2, 2, 3, 2};
                row4 = new int[]{0, 3, 0, 3, 0};

                grid = new int[][]{row0, row1, row2, row3, row4};

                row0_r = new boolean[]{false, false, false, false, false};
                row1_r = new boolean[]{true, false, true, false, false};
                row2_r = new boolean[]{false, false, false, false, false};
                row3_r = new boolean[]{true, false, false, false, false};
                row4_r = new boolean[]{false, false, false, false, false};

                grid_r = new boolean[][]{row0_r, row1_r, row2_r, row3_r, row4_r};

                configGrid(board, grid, grid_r);
                break;
            default:
        }
    }

    private void configGrid(Board board, int [][] config, boolean[][] config_r){

        for (int i = 0; i < board.getSIZE(); i++) {
            for (int j = 0; j < board.getSIZE(); j++) {
                board.getGrid()[i][j].setFloor(config[i][j]);
                board.getGrid()[i][j].setRoof(config_r[i][j]);
            }
        }
        model.notifyObservers(new DataBoard(LightConversion.getLightVersion(board)));
    }

    private int readInt(int size){
        if(scanner.hasNextInt()){
            int i = scanner.nextInt();
            if(i <= size && i >= 0) {
                return i;
            } else {
                System.out.println("\nInvalid Choice (integer out of bound), please select again:");
                return readInt(size);}
        }
        else{
            System.out.println("\nInvalid Choice (mismatch input type), please select again:");
            scanner.next();
            return readInt(size);
        }
    }
}
