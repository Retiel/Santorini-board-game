package it.polimi.ingsw.PSP33.view.cli;

import java.io.PrintStream;
import java.util.Scanner;

public class InOutCLI {
    private Scanner scanner;
    private PrintStream printer;

    /**
     * the InOutCLI constuctor
     */
    public InOutCLI(){
        this.scanner = new Scanner(System.in);
        this.printer = new PrintStream(System.out);
    }


    public Scanner getScanner() {
        return scanner;
    }

    public void setScanner(Scanner scanner) {
        this.scanner = scanner;
    }

    public PrintStream getPrinter() {
        return printer;
    }

    public void setPrinter(PrintStream printer) {
        this.printer = printer;
    }
}
