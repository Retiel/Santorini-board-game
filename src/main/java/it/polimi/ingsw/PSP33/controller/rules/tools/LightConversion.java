package it.polimi.ingsw.PSP33.controller.rules.tools;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;
import it.polimi.ingsw.PSP33.model.Player;
import it.polimi.ingsw.PSP33.model.light_version.LightBoard;
import it.polimi.ingsw.PSP33.model.light_version.LightCell;
import it.polimi.ingsw.PSP33.model.light_version.LightPawn;
import it.polimi.ingsw.PSP33.model.light_version.LightPlayer;

/**
 * Conversion class used to convert some class of the model in his light version
 * */
public class LightConversion {

    /**
     * Conversion for the Pawn class
     * @param pawn pawn type object
     *
     * @return LightPawn object
     */
    public static LightPawn getLightVersion(Pawn pawn){
        return new LightPawn(pawn.getColor(), pawn.getNumber());
    }

    /**
     * Conversion for the Cell class
     * @param cell cell type object
     *
     * @return LightCell object
     */
    public static LightCell getLightVersion(Cell cell){
        if (cell.getOccupied() == null) return new LightCell(cell.getCoord(), cell.getFloor(), cell.isRoof(), null);

        LightPawn lightPawn = LightConversion.getLightVersion(cell.getOccupied());
        return new LightCell(cell.getCoord(), cell.getFloor(), cell.isRoof(), lightPawn);
    }

    /**
     * Conversion for the Player class
     * @param player player type object
     *
     * @return LightPlayer object
     */
    public static LightPlayer getLightVersion(Player player){
        return new LightPlayer(player.getName(), player.getColor(), player.getCard());
    }

    /**
     * Conversion for the Board class
     * @param board board type object
     *
     * @return LightBoard object
     */
    public static LightBoard getLightVersion(Board board){
        LightCell[][] cells = new LightCell[board.getSIZE()][board.getSIZE()];

        for(Cell[] rows : board.getGrid()){
            for (Cell cell : rows){
                cells[cell.getCoordX()][cell.getCoordY()] = LightConversion.getLightVersion(cell);
            }
        }

        return new LightBoard(cells);
    }
}
