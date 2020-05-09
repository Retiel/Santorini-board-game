package it.polimi.ingsw.PSP33.controller.rules.tools;

import it.polimi.ingsw.PSP33.model.Board;
import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;
import it.polimi.ingsw.PSP33.model.Player;
import it.polimi.ingsw.PSP33.model.light_version.LightBoard;
import it.polimi.ingsw.PSP33.model.light_version.LightCell;
import it.polimi.ingsw.PSP33.model.light_version.LightPawn;
import it.polimi.ingsw.PSP33.model.light_version.LightPlayer;

public class LightConvertion {

    public static LightPawn getLightVersion(Pawn pawn){
        return new LightPawn(pawn.getColor(), pawn.getNumber());
    }

    public static LightCell getLightVersion(Cell cell){
        if (cell.getOccupied() == null) return new LightCell(cell.getCoord(), cell.getFloor(), cell.isRoof(), null);

        LightPawn lightPawn = LightConvertion.getLightVersion(cell.getOccupied());
        return new LightCell(cell.getCoord(), cell.getFloor(), cell.isRoof(), lightPawn);
    }

    public static LightPlayer getLightVersion(Player player){
        return new LightPlayer(player.getName(), player.getColor(), player.getCard());
    }

    public static LightBoard getLightVersion(Board board){
        LightCell[][] cells = new LightCell[board.getSIZE()][board.getSIZE()];

        for(Cell[] rows : board.getGrid()){
            for (Cell cell : rows){
                cells[cell.getCoordX()][cell.getCoordY()] = LightConvertion.getLightVersion(cell);
            }
        }

        return new LightBoard(cells);
    }
}
