package it.polimi.ingsw.PSP33.controller.godsRules;

public class BuildContext {

    private Build build;

    public BuildContext(String godName) {

        switch (godName){
            case "Atlas": this.build = new BuildBasic();
            default:
                throw new IllegalStateException("Unexpected value: " + godName);
        }
    }
}
