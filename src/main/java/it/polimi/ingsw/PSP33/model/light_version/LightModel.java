package it.polimi.ingsw.PSP33.model.light_version;

import java.util.List;

public class LightModel {

    private LightCell[][] lightGrid;
    private List<LightPlayer> players;
    private String playerName;

    public LightCell[][] getLightGrid() {
        return lightGrid;
    }

    public void setLightGrid(LightCell[][] lightGrid) {
        this.lightGrid = lightGrid;
    }

    public List<LightPlayer> getPlayers() {
        return players;
    }

    public void setPlayers(List<LightPlayer> players) {
        this.players = players;
    }

    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }
}
