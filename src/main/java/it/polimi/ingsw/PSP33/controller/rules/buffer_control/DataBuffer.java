package it.polimi.ingsw.PSP33.controller.rules.buffer_control;

import it.polimi.ingsw.PSP33.model.Cell;
import it.polimi.ingsw.PSP33.model.Pawn;

public class DataBuffer {

    private Cell oldPosition;
    private Cell newPosition;

    private Cell oldBuild;
    private Cell newBuild;

    private Cell oldExtra;
    private Cell newExtra;

    private Pawn currentPawn;

    public Cell getOldPosition() {
        return oldPosition;
    }

    public void setOldPosition(Cell oldPosition) {
        this.oldPosition = oldPosition;
    }

    public Cell getNewPosition() {
        return newPosition;
    }

    public void setNewPosition(Cell newPosition) {
        this.newPosition = newPosition;
    }

    public Cell getOldBuild() {
        return oldBuild;
    }

    public void setOldBuild(Cell oldBuild) {
        this.oldBuild = oldBuild;
    }

    public Cell getNewBuild() {
        return newBuild;
    }

    public void setNewBuild(Cell newBuild) {
        this.newBuild = newBuild;
    }

    public Cell getOldExtra() {
        return oldExtra;
    }

    public void setOldExtra(Cell oldExtra) {
        this.oldExtra = oldExtra;
    }

    public Cell getNewExtra() {
        return newExtra;
    }

    public void setNewExtra(Cell newExtra) {
        this.newExtra = newExtra;
    }

    public Pawn getCurrentPawn() {
        return currentPawn;
    }

    public void setCurrentPawn(Pawn currentPawn) {
        this.currentPawn = currentPawn;
    }
}
