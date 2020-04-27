package it.polimi.ingsw.PSP33.view.cli;

import java.util.ArrayList;

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

    /**
     * methods of the printer to print any type of list.
     * @param inputlist the list to print
     */
    public void printList(ArrayList<String> inputlist){
        //define Arraylist content
        int counter=0;
        for (String str : inputlist) {
            counter++;
            System.out.println("\n"+(counter));
            System.out.println(str);
        }
        System.out.println("\n");
    }

    /*todo: printer methods for all the menù parts*/
}
