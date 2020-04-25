package it.polimi.ingsw.PSP33.controller.rules.buffer_control;

public class FlagControl {

    public static boolean checkStart(String name){
        if (name.equals("Prometheus")) return true;
        return false;
    }

    public static boolean checkMove(String name){
        if (name.equals("Prometheus")) return true;
        return false;
    }

    public static boolean checkBuild(String name){
        if (name.equals("Atlas")) return true;
        return false;
    }

    public static String checkStringExtra(String name){
        switch (name){
            case "Artemis": return "move";
            case "Demeter":
            case "Hephaestus":
                return "build";
            default: return "nil";
        }
    }

}
