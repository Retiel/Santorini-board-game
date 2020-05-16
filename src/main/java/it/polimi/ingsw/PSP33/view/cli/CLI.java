package it.polimi.ingsw.PSP33.view.cli;

import it.polimi.ingsw.PSP33.events.toClient.data.DataCell;
import it.polimi.ingsw.PSP33.events.toClient.data.DataBoard;
import it.polimi.ingsw.PSP33.events.toClient.data.DataPawn;
import it.polimi.ingsw.PSP33.events.toClient.data.DataPlayer;
import it.polimi.ingsw.PSP33.events.toClient.setup.CurrentPlayer;
import it.polimi.ingsw.PSP33.events.toClient.setup.PossiblePlacement;
import it.polimi.ingsw.PSP33.events.toClient.setup.SelectGods;
import it.polimi.ingsw.PSP33.events.toClient.setup.YourGod;
import it.polimi.ingsw.PSP33.events.toClient.turn.*;
import it.polimi.ingsw.PSP33.events.toServer.setup.ChoosenGod;
import it.polimi.ingsw.PSP33.events.toServer.setup.PlacePawn;
import it.polimi.ingsw.PSP33.events.toServer.setup.SelectedGods;
import it.polimi.ingsw.PSP33.events.toServer.turn.*;
import it.polimi.ingsw.PSP33.model.God;
import it.polimi.ingsw.PSP33.model.Pawn;
import it.polimi.ingsw.PSP33.model.light_version.*;
import it.polimi.ingsw.PSP33.utils.Coord;
import it.polimi.ingsw.PSP33.utils.enums.Color;
import it.polimi.ingsw.PSP33.view.AbstractView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * the rapppresentation of the View Class for the CLI mode
 */
public class CLI extends AbstractView {

    private LightBoard board;
    private LightCell[][] lightGrid;
    private List<LightPlayer> player;
    private LightModel lightModel;
    private int pawnSelected;
    private CLIPrinter cliPrinter;
    private Scanner scanner;

    public CLI() {
        cliPrinter = new CLIPrinter();
        scanner = new Scanner(System.in);

    }

    @Override
    public void visit(DataBoard dataBoard) {
        //set up client board
        board = dataBoard.getGrid();
        lightGrid = board.getGrid();
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
        //todo: correct deleting the Pawn attribute and update the board
        dataPawn.getPawn();
    }

    @Override
    public void visit(YourGod yourGod) {
        int i;
        System.out.println("\nWhich God Card do you want?");
        cliPrinter.printGodList(yourGod.getGods());

        ChoosenGod cg = new ChoosenGod(yourGod.getGods().get(readInput(yourGod.getGods().size()) - 1));
        notifyObservers(cg);
    }

    @Override
    public void visit(CurrentPlayer currentPlayer) {

    }

    @Override
    public void visit(PossiblePlacement possiblePlacement) {
        cliPrinter.printBoard(board);
        //print 2 times the placement for the pawn (setup phase)
        System.out.println("\nWhere do you want to place your worker?");
        cliPrinter.printList(possiblePlacement.getCoordList());

        System.out.println("client turn");
        PlacePawn pp = new PlacePawn(possiblePlacement.getCoordList().get(readInput(possiblePlacement.getCoordList().size()) - 1));
        notifyObservers(pp);
    }

    @Override
    public void visit(SelectGods selectGods) {
        List<God> allGods = new ArrayList<>(selectGods.getGods());
        List<God> chosenGods = new ArrayList<>();

        for(int c=0;c<player.size();c++){
            System.out.println("Choose the "+c+"° God:");
            cliPrinter.printGodList(allGods);
            int i = readInput(allGods.size());
            chosenGods.add(allGods.get(i-1));
            allGods.remove(allGods.get(i-1));
        }

        SelectedGods sg = new SelectedGods(chosenGods);
        notifyObservers(sg);
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
        int j;
        boolean beginning = true;
        //print board
        cliPrinter.printBoard(board);

        RequestPossibleBuild rpb;
        RequestPossibleMove rpm;
        RequestExtraAction rea;

        if (!newAction.isExtra() && !newAction.isBuild() && !newAction.isMove()){
            beginning = true;
            NewTurn newTurn = new NewTurn();
            notifyObservers(newTurn);
        } else{
            if(beginning) {
                System.out.println("\nWhich pawn you want to use? (1 or 2)");
                pawnSelected = scanner.nextInt();
                beginning = false;
            }


            //decide action with the Boolean and send input to controller (switch case)
            if (newAction.isMove()){
                if (!newAction.isExtra()){
                    //send move action to controller
                    rpm = new RequestPossibleMove(pawnSelected);
                    notifyObservers(rpm);
                }
                else {
                    //choose and create possible move or extra message to notify to the controller
                    System.out.println("\nWhat type of action do you want to do?\n1) Move\n2) Extra");
                    j = scanner.nextInt();
                    if (j==1){
                        rpm = new RequestPossibleMove(pawnSelected);
                        notifyObservers(rpm);
                    } else if (j == 2){
                        rea = new RequestExtraAction(pawnSelected);
                        notifyObservers(rea);
                    }
                }
            }

            if (newAction.isBuild()){

                if (!newAction.isExtra()){
                    rpb = new RequestPossibleBuild(pawnSelected);
                    notifyObservers(rpb);
                }
                else{
                    //choose your action and send proper message to server
                    System.out.println("\nWhat type of action do you want to do?\n1) Build\n2) Extra");
                    j = scanner.nextInt();

                    if (j == 1){
                        rpb = new RequestPossibleBuild(pawnSelected);
                        notifyObservers(rpb);
                    } else if (j == 2){
                        rea = new RequestExtraAction(pawnSelected);
                        notifyObservers(rea);
                    }
                }
            }

            if (newAction.isExtra()){
                if (!newAction.isBuild()&&!newAction.isMove()){
                    rea = new RequestExtraAction(pawnSelected);
                    notifyObservers(rea);
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
        int i = readInput(possibleBuild.getCoordList().size() + possibleBuild.getGodsList().size());

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
        int i = readInput(possibleMove.getCoordList().size() + possibleMove.getGodsList().size());

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

        //send info to controller
        ExtraAction ea;
        Coord choiceCoord = possibleExtraAction.getCoordList().get(readInput(possibleExtraAction.getCoordList().size()) - 1);
        ea = new ExtraAction(choiceCoord);
        notifyObservers(ea);

    }

    private int readInput(int size){
        int i = scanner.nextInt();

        if(i <= size && i > 0) return i;
        return readInput(size);
    }

}
