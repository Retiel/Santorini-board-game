package it.polimi.ingsw.PSP33.server;

import it.polimi.ingsw.PSP33.controller.rules.tools.LightConvertion;
import it.polimi.ingsw.PSP33.events.toClient.data.DataBoard;
import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Arrays;
import java.util.Scanner;

/**
 * Server class that handles the connection requests from clients
 */
public class Server {

    /**
     * Server's socket
     */
    private static ServerSocket serverSocket = null;

    /**
     * Server's socket port
     */
    public final static int SOCKET_PORT = 7777;

    public static void main(String[] args) {
        try {
            //Open server's socket
            serverSocket = new ServerSocket(SOCKET_PORT);
        } catch (IOException e) {
            System.out.println("Cannot open server socket");
            System.exit(1);
        }

        while(true) {
            try {
                Socket client = serverSocket.accept();

                ClientHandler clientHandler = new ClientHandler(client);

                Thread thread = new Thread(clientHandler, "Server" + client.getInetAddress());
                thread.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /* Setting cheat method */
    public void listenInput(Model model){
        new Thread(() -> {
            Scanner scanner = new Scanner(System.in);
            while(true){
                String string = scanner.nextLine();
                switch (string){
                    case "test": System.out.println("Test, it works"); break;
                    case "mode1":
                        config1Model(model);
                        break;
                    case "mode2":
                        config2Model(model);
                        break;
                    default:
                }
            }
        }).start();
    }

    private void config1Model(Model model) {
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
        Board board = model.getBoard();
        int[] row0 = {0,1,0,0,1};
        int[] row1 = {0,2,3,1,1};
        int[] row2 = {0,3,0,0,2};
        int[] row3 = {0,2,2,0,2};
        int[] row4 = {0,1,0,3,0};

        int[][] grid = {row0, row1, row2, row3, row4};

        boolean[] row0_r = {false, false, false, false, false};
        boolean[] row1_r = {true, false, true, false, false};
        boolean[] row2_r = {false, false, false, false, false};
        boolean[] row3_r = {true, false, false, false, false};
        boolean[] row4_r = {false, false, false, false, false};

        boolean[][] grid_r = {row0_r, row1_r, row2_r, row3_r, row4_r};

        configGrid(board, grid, grid_r);
        model.notifyObservers(new DataBoard(LightConvertion.getLightVersion(board)));
    }

    private void config2Model(Model model){
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
        Board board = model.getBoard();

        int[] row0 = {3,3,2,0,1};
        int[] row1 = {1,0,3,1,1};
        int[] row2 = {1,3,0,0,2};
        int[] row3 = {3,2,2,3,2};
        int[] row4 = {0,3,0,3,0};

        int[][] grid = {row0, row1, row2, row3, row4};

        boolean[] row0_r = {false, false, false, false, false};
        boolean[] row1_r = {true, false, true, false, false};
        boolean[] row2_r = {false, false, false, false, false};
        boolean[] row3_r = {true, false, false, false, false};
        boolean[] row4_r = {false, false, false, false, false};

        boolean[][] grid_r = {row0_r, row1_r, row2_r, row3_r, row4_r};

        configGrid(board, grid, grid_r);
        model.notifyObservers(new DataBoard(LightConvertion.getLightVersion(board)));
    }

    private void configGrid(Board board, int [][] config, boolean[][] config_r){

        for (int i = 0; i < board.getSIZE(); i++) {
            for (int j = 0; j < board.getSIZE(); j++) {
                board.getGrid()[i][j].setFloor(config[i][j]);
                board.getGrid()[i][j].setRoof(config_r[i][j]);
            }
        }
    }
}
