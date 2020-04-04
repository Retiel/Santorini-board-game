package it.polimi.ingsw.PSP33.model;


/**
 * Class God that defines a generic god card, class used to hold name and description
 *
 * */
public class God {

    private String godName;
    private String description;

    public God(String godName, String description) {
        this.godName = godName;
        this.description = description;
    }

    public String getName() {
        return godName;
    }

    public String getDescription() {
        return description;
    }
}
