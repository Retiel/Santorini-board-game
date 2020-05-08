package it.polimi.ingsw.PSP33.model;


import it.polimi.ingsw.PSP33.utils.enums.Gods;

/**
 * Class God that defines a generic god card, class used to hold name and description
 *
 * */
public class God {

    /**
     * Name of the god
     */
    private final Gods name;

    /**
     * Description of the effect of the god
     */
    private final String description;

    /**
     * Constructor of the class
     * @param name name of teh god
     * @param description descriptio of the effect of the card
     */
    public God(Gods name, String description) {
        this.name = name;
        this.description = description;
    }

    /**
     * Method to get the name of the card
     *
     * @return String type
     */
    public Gods getName() {
        return name;
    }

    /**
     * Method to get the description of the card
     *
     * @return String type
     */
    public String getDescription() {
        return description;
    }
}
