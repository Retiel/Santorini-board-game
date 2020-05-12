package it.polimi.ingsw.PSP33.view.cli;

import it.polimi.ingsw.PSP33.events.toClient.data.DataCell;
import it.polimi.ingsw.PSP33.events.toClient.data.DataGrid;
import it.polimi.ingsw.PSP33.events.toClient.data.DataPawn;
import it.polimi.ingsw.PSP33.events.toClient.data.DataPlayer;
import it.polimi.ingsw.PSP33.events.toClient.setup.CurrentPlayer;
import it.polimi.ingsw.PSP33.events.toClient.setup.PossiblePlacement;
import it.polimi.ingsw.PSP33.events.toClient.turn.*;
import it.polimi.ingsw.PSP33.events.toServer.setup.PlacePawn;
import it.polimi.ingsw.PSP33.events.toServer.turn.*;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Pawn;
import it.polimi.ingsw.PSP33.model.Player;
import it.polimi.ingsw.PSP33.model.light_version.LightBoard;
import it.polimi.ingsw.PSP33.model.light_version.LightCell;
import it.polimi.ingsw.PSP33.model.light_version.LightPawn;
import it.polimi.ingsw.PSP33.model.light_version.LightPlayer;
import it.polimi.ingsw.PSP33.utils.Coord;
import it.polimi.ingsw.PSP33.utils.enums.Color;
import it.polimi.ingsw.PSP33.view.AbstractView;

import java.util.Scanner;

/**
 * the rapppresentation of the View Class for the CLI mode
 */
public class CLI extends AbstractView {

    private static LightPlayer player;
    private static CLIPrinter cliPrinter;
    private static LightBoard board;
    private static Scanner scanner;
    private static LightCell[][] lightGrid;
    private static LightPawn pawn = new LightPawn(Color.BLUE, 1);

    public static void main(String[] args) {
        lightGrid = new LightCell[5][5];
        int i = 0;
        int j = 0;
        while (j<5){
            while (i<5){
                Coord coord = new Coord(i,j);
                lightGrid[i][j] = new LightCell(coord,0,false, null);
                i++;
            }
            j++;
            i = 0;
        }
        board = new LightBoard(lightGrid);
        cliPrinter = new CLIPrinter();
        scanner = new Scanner(System.in);

        cliPrinter.printBoard(board);

    }

    @Override
    public void visit(DataGrid dataGrid) {
        //set up client board
        board = dataGrid.getGrid();
    }

    @Override
    public void visit(DataCell dataCell) {

        //change old cell with new
        if (dataCell.getOldCell() != null){
            int oldx = dataCell.getOldCell().getCoord().getX();
            int oldy = dataCell.getOldCell().getCoord().getY();
            board.getGrid()[oldx][oldy] = dataCell.getOldCell();
        }

        int newx = dataCell.getNewCell().getCoord().getX();
        int newy = dataCell.getNewCell().getCoord().getY();
        board.getGrid()[newx][newy] = dataCell.getNewCell();
    }

    @Override
    public void visit(DataPlayer dataPlayer) {

        //update player info at the beginning
        player = dataPlayer.getPlayer();
    }

    @Override
    public void visit(DataPawn dataPawn) {

        pawn = dataPawn.getPawn();
    }

    @Override
    public void visit(CurrentPlayer currentPlayer) {

    }

    @Override
    public void visit(PossiblePlacement possiblePlacement) {
        //print 2 times the placement for the pawn (setup phase)
        System.out.println("\nWhere do you want to place your worker?");
        cliPrinter.printList(possiblePlacement.getCoordList());
        int i = scanner.nextInt();

        PlacePawn pp = new PlacePawn(possiblePlacement.getCoordList().get(i-1));
    }

    @Override
    public void visit(YouLose youLose) {
        System.out.println("You lose!");
    }

    @Override
    public void visit(YouWin youWin) {
        System.out.println("You win!");
    }

    @Override
    public void visit(NewAction newAction) {
        int i;
        int j;
        //print board
        cliPrinter.printBoard(board);

        RequestPossibleBuild rpb;
        RequestPossibleMove rpm;
        RequestExtraAction rea;

        if (!newAction.isExtra() && !newAction.isBuild() && !newAction.isMove()){
            NewTurn newTurn = new NewTurn();
            notifyObservers(newTurn);
        } else{
            //select which pawn you want to use
            System.out.println("\nWhich pawn you want to use? (1 or 2)");
            i = scanner.nextInt();



            //decide action with the Boolean and send input to controller (switch case)
            if (newAction.isMove()){
                if (!newAction.isExtra()){
                    //send move action to controller
                    rpm = new RequestPossibleMove(i);
                    notifyObservers(rpm);
                }
                else {
                    //choose and create possible move or extra message to notify to the controller
                    System.out.println("\nWhat type of action do you want to do?\n1) Move\n2) Extra");
                    j = scanner.nextInt();
                    if (j==1){
                        rpm = new RequestPossibleMove(i);
                        notifyObservers(rpm);
                    } else if (j == 2){
                        rea = new RequestExtraAction(i);
                        notifyObservers(rea);
                    }
                }
            }

            if (newAction.isBuild()){

                if (!newAction.isExtra()){
                    rpb = new RequestPossibleBuild(i);
                    notifyObservers(rpb);
                }
                else{
                    //choose your action and send proper message to server
                    System.out.println("\nWhat type of action do you want to do?\n1) Build\n2) Extra");
                    j = scanner.nextInt();

                    if (j == 1){
                        rpb = new RequestPossibleBuild(i);
                        notifyObservers(rpb);
                    } else if (j == 2){
                        rea = new RequestExtraAction(i);
                        notifyObservers(rea);
                    }
                }
            }
        }
    }

    @Override
    public void visit(PossibleBuild possibleBuild) {
        //print board
        cliPrinter.printBoard(board);
        //print choices and read player's intentions
        System.out.println("\nWhere do you want to build your Block?\n");
        cliPrinter.printList(possibleBuild.getCoordList());
        cliPrinter.printSecondList(possibleBuild.getGodsList(),possibleBuild.getCoordList().size());
        int i = scanner.nextInt();

        //send info to controller
        //todo: check real funcion of floor boolean
        Coord choiceCoord;
        BuildAction ba;
        if(i <= possibleBuild.getCoordList().size()){
            choiceCoord = possibleBuild.getCoordList().get(i-1);
            if(board.getGrid()[choiceCoord.getX()][choiceCoord.getY()].getFloor()<2) {
                ba = new BuildAction(choiceCoord, false);
            }
            else ba = new BuildAction(choiceCoord, true);

        }
        else{
            choiceCoord = possibleBuild.getGodsList().get(i-possibleBuild.getCoordList().size()-1);
            if(board.getGrid()[choiceCoord.getX()][choiceCoord.getY()].getFloor()<2){
                ba = new BuildAction(choiceCoord,false);
            } else ba = new BuildAction(choiceCoord,false);
        }
        notifyObservers(ba);
    }

    @Override
    public void visit(PossibleMove possibleMove) {
        //print board
        cliPrinter.printBoard(board);
        //print choices and read player's intentions
        System.out.println("\nWhere do you want to move your worker?\n");
        cliPrinter.printList(possibleMove.getCoordList());
        cliPrinter.printSecondList(possibleMove.getGodsList(),possibleMove.getCoordList().size());
        int i = scanner.nextInt();

        //send info to controller
        MoveAction ma;
        if(i <= possibleMove.getCoordList().size()){
            ma = new MoveAction(possibleMove.getCoordList().get(i - 1));
        } else {
            ma = new MoveAction(possibleMove.getGodsList().get(i - possibleMove.getCoordList().size() - 1));
        }
        notifyObservers(ma);
    }

    @Override
    public void visit(PossibleExtraAction possibleExtraAction) {
        //print board
        cliPrinter.printBoard(board);
        //print choices and read player's intentions
        System.out.println("\n");
        cliPrinter.printList(possibleExtraAction.getCoordList());
        int i = scanner.nextInt();

        //send info to controller
        ExtraAction ea;
        Coord choiceCoord = possibleExtraAction.getCoordList().get(i-1);
        ea = new ExtraAction(choiceCoord);
        notifyObservers(ea);

    }

}
