package it.polimi.ingsw.PSP33.model;


/**
 * Class GodCard that defines a generic god card, class used to hold name and description
 *
 * */
public class GodCard {

    private String godName;
    private String description;

    public GodCard(String godName, String description) {
        this.godName = godName;
        this.description = description;
    }

    public String getGodName() {
        return godName;
    }

    public String getDescription() {
        return description;
    }
}
