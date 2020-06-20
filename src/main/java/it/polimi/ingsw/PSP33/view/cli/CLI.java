package it.polimi.ingsw.PSP33.view.cli;

import it.polimi.ingsw.PSP33.events.toClient.data.DataCell;
import it.polimi.ingsw.PSP33.events.toClient.data.DataBoard;
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
import it.polimi.ingsw.PSP33.model.light_version.*;
import it.polimi.ingsw.PSP33.utils.Coord;
import it.polimi.ingsw.PSP33.view.AbstractView;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * the rapppresentation of the View Class for the CLI mode
 */
public class CLI extends AbstractView {

    private LightModel lightModel;
    private CLIPrinter cliPrinter;

    private ExecutorService executor;

    /**
     * the constructor of the CLI class
     */
    public CLI() {
        cliPrinter = new CLIPrinter();
        lightModel = new LightModel();

        executor = Executors.newSingleThreadExecutor();
    }

    /**
     * visit implementation method that configure the board of the client
     * @param dataBoard
     */
    @Override
    public void visit(DataBoard dataBoard) {
        //set up client board
        lightModel.setLightGrid(dataBoard.getGrid().getGrid());
    }

    /**
     * visit implementation method that configure one single Cell of the client
     * @param dataCell
     */
    @Override
    public void visit(DataCell dataCell) {

        //change old cell with new
        if (dataCell.getOldCell() != null){
            int oldx = dataCell.getOldCell().getCoord().getX();
            int oldy = dataCell.getOldCell().getCoord().getY();
            lightModel.getLightGrid()[oldx][oldy] = dataCell.getOldCell();
        }

        int newx = dataCell.getNewCell().getCoord().getX();
        int newy = dataCell.getNewCell().getCoord().getY();
        lightModel.getLightGrid()[newx][newy] = dataCell.getNewCell();

        if(!lightModel.isYourTurn()) {
            System.out.println("\nAn opponent made a move and changed the Board:");
            cliPrinter.printBoard(lightModel);
        }
    }

    /**
     * method for the players data setup
     * @param dataPlayer
     */
    @Override
    public void visit(DataPlayer dataPlayer) {
        //update player info at the beginning
        lightModel.setPlayers(dataPlayer.getPlayer());
        lightModel.setPlayerName(dataPlayer.getName());
    }

    /**
     * method for the choice of the God used in the game
     * @param yourGod
     */
    @Override
    public void visit(YourGod yourGod) {

        executor.execute(() -> {
            System.out.println("\nWhich God Card do you want?\n");
            cliPrinter.printGodList(yourGod.getGods());

            ChoosenGod cg = new ChoosenGod(yourGod.getGods().get(readInput(yourGod.getGods().size()) - 1));
            notifyObservers(cg);
            lightModel.setYourTurn(false);
        });
    }

    /**
     * visit method for the current player
     * @param currentPlayer
     */
    @Override
    public void visit(CurrentPlayer currentPlayer) {
        /* sinkhole used for server*/
    }

    /**
     * visit method for the initial placement of the workers
     * @param possiblePlacement
     */
    @Override
    public void visit(PossiblePlacement possiblePlacement) {

        executor.execute(() -> {
            cliPrinter.printBoard(lightModel);
            //print 2 times the placement for the pawn (setup phase)
            System.out.println("\nWhere do you want to place your worker?");
            cliPrinter.printList(possiblePlacement.getCoordList());
            PlacePawn pp = new PlacePawn(possiblePlacement.getCoordList().get(readInput(possiblePlacement.getCoordList().size()) - 1));
            notifyObservers(pp);
        });
    }

    /**
     * visit method which display the available god cards to the player
     * @param selectGods
     */
    @Override
    public void visit(SelectGods selectGods) {

        executor.execute(() -> {
            List<God> allGods = new ArrayList<>(selectGods.getGods());
            List<God> chosenGods = new ArrayList<>();

            for(int c=1;c<lightModel.getPlayers().size()+1;c++){
                System.out.println("Choose the "+c+"° God:\n");
                cliPrinter.printGodList(allGods);
                int i = readInput(allGods.size());
                chosenGods.add(allGods.get(i-1));
                allGods.remove(allGods.get(i-1));
            }

            SelectedGods sg = new SelectedGods(chosenGods);
            notifyObservers(sg);
        });
    }

    /**
     * method that notify the player the outcome of the game
     * @param youLose message to communicate the lose
     */
    @Override
    public void visit(YouLose youLose) {
        System.out.println("Player " + youLose.getName() + "has won!");
    }

    /**
     * method that notify the player the outcome of the game
     * @param youWin message to communicate the Win
     */
    @Override
    public void visit(YouWin youWin) {
        System.out.println("You win!");
    }

    /**
     * visit method that permit the player to select the main worker
     * @param selectPawn message to communicate the necessity to select pawn
     */
    @Override
    public void visit(SelectPawn selectPawn) {

        executor.execute(() -> {
            int c = selectPawn.getValue();
            switch (c) {
                default:
                    cliPrinter.printBoard(lightModel);
                    System.out.println("\nWhich worker you want to use? (1 or 2)");
                    SelectedPawn sp1 = new SelectedPawn(readInput(2));
                    notifyObservers(sp1);
                    break;

                case 1:
                    System.out.println("You can move only the worker number 1\n");
                    SelectedPawn sp2 = new SelectedPawn(c);
                    notifyObservers(sp2);
                    break;

                case 2:
                    System.out.println("You can move only the worker number 2\n");
                    SelectedPawn sp3 = new SelectedPawn(c);
                    notifyObservers(sp3);
                    break;
            }
        });
    }

    @Override
    public void visit(NewAction newAction) {
        executor.execute(() -> {
            int j;

            RequestPossibleBuild rpb;
            RequestPossibleMove rpm;
            RequestExtraAction rea;

            if (!newAction.isExtra() && !newAction.isBuild() && !newAction.isMove()){
                NewTurn newTurn = new NewTurn();
                notifyObservers(newTurn);

                cliPrinter.printBoard(lightModel);
                System.out.println("End of the turn:");
                lightModel.setYourTurn(false);
            } else{

                lightModel.setYourTurn(true);
                //decide action with the Boolean and send input to controller (switch case)
                if (newAction.isMove()){
                    if (!newAction.isExtra()){
                        //send move action to controller
                        rpm = new RequestPossibleMove();
                        notifyObservers(rpm);
                    }
                    else {
                        //choose and create possible move or extra message to notify to the controller
                        cliPrinter.printBoard(lightModel);
                        System.out.println("What type of action do you want to do?\n1) Move\n2) God Effect");
                        //System.out.println(lightModel.getPlayers().get(playerID).getCard().getDescription());
                        j = readInput(2);
                        if (j==1){
                            rpm = new RequestPossibleMove();
                            notifyObservers(rpm);
                        } else if (j == 2){
                            rea = new RequestExtraAction();
                            notifyObservers(rea);
                        }
                    }
                }

                if (newAction.isBuild()){

                    if (!newAction.isExtra()){
                        rpb = new RequestPossibleBuild();
                        notifyObservers(rpb);
                    }
                    else{
                        //choose your action and send proper message to server
                        cliPrinter.printBoard(lightModel);
                        System.out.print("What type of action do you want to do?\n1) Build\n2) God Effect");
                        //System.out.println(lightModel.getPlayers().get(playerID).getCard().getDescription());
                        j = readInput(2);

                        if (j == 1){
                            rpb = new RequestPossibleBuild();
                            notifyObservers(rpb);
                        } else if (j == 2){
                            rea = new RequestExtraAction();
                            notifyObservers(rea);
                        }
                    }
                }

                if (newAction.isExtra()){
                    if (!newAction.isBuild()&&!newAction.isMove()){
                        rea = new RequestExtraAction();
                        notifyObservers(rea);
                    }
                }
            }
        });
    }

    @Override
    public void visit(PossibleBuild possibleBuild) {
        executor.execute(() -> {
            //print board
            cliPrinter.printBoard(lightModel);
            //print choices and read player's intentions
            System.out.println("Where do you want to build your Block?\n");
            cliPrinter.printList(possibleBuild.getCoordList());
            cliPrinter.printSecondList(possibleBuild.getGodsList(),possibleBuild.getCoordList().size());
            int i = readInput(possibleBuild.getCoordList().size() + possibleBuild.getGodsList().size());

            //send info to controller
            Coord choiceCoord;
            BuildAction ba;
            if(i <= possibleBuild.getCoordList().size()){
                choiceCoord = possibleBuild.getCoordList().get(i-1);
                if(lightModel.getLightGrid()[choiceCoord.getX()][choiceCoord.getY()].getFloor()<2) {
                    ba = new BuildAction(choiceCoord, false);
                }
                else ba = new BuildAction(choiceCoord, true);

            }
            else{
                choiceCoord = possibleBuild.getGodsList().get(i-possibleBuild.getCoordList().size()-1);
                if(lightModel.getLightGrid()[choiceCoord.getX()][choiceCoord.getY()].getFloor()<2){
                    ba = new BuildAction(choiceCoord,false);
                } else ba = new BuildAction(choiceCoord,false);
            }
            notifyObservers(ba);
        });
    }

    @Override
    public void visit(PossibleMove possibleMove) {
        executor.execute(() -> {
            //print board
            cliPrinter.printBoard(lightModel);
            //print choices and read player's intentions
            System.out.println("Where do you want to move your worker?\n");
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
        });
    }

    @Override
    public void visit(PossibleExtraAction possibleExtraAction) {
        executor.execute(() -> {
            //print board
            cliPrinter.printBoard(lightModel);
            //print choices and read player's intentions
            System.out.println("Where do you want to use Your God Action?");
            cliPrinter.printSecondList(possibleExtraAction.getCoordList(),0);

            //send info to controller
            ExtraAction ea;
            Coord choiceCoord = possibleExtraAction.getCoordList().get(readInput(possibleExtraAction.getCoordList().size()) - 1);
            ea = new ExtraAction(choiceCoord);
            notifyObservers(ea);
        });

    }

    private int readInput(int size){

        Scanner scanner = new Scanner(System.in);
        if(scanner.hasNextInt()){
            int i = scanner.nextInt();
            if(i <= size && i > 0) {
                return i;
            } else {
                System.out.println("Invalid Choice (integer out of bound), please select again:");
                return readInput(size);}
        }
        else {
            System.out.println("Invalid Choice (mismatch input type), please select again:");
            scanner.next();
            return readInput(size);
        }
    }
}
