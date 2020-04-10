package it.polimi.ingsw.PSP33.controller.rules.build;

public class BuildContext {

    private Build build;

    public BuildContext(String godName) {
        switch (godName){
            case "ATLAS":
                break;

            default:
                this.build = new BuildDefault();
                throw new IllegalStateException("Unexpected value: " + godName);
        }
    }
}
