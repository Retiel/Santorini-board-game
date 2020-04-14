package it.polimi.ingsw.PSP33.view.cli;

/**
 * The class that permit to print all the possible outputs that you want to show to the player
 */
public class PrintOutput {

    private InOutCLI reader;

    public PrintOutput(InOutCLI inOutCLI){
        this.reader = inOutCLI;
    }

    public void print(String output){
        reader.getPrinter().print(output);
    }

    public void println(String output){
        reader.getPrinter().println(output);
    }

    /*todo: printer methods for all the menù parts*/
}
