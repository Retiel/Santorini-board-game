package it.polimi.ingsw.PSP33.controller.rules;

public class FlagControl {

    public static boolean checkStart(String name){
        if (name.equals("Prometheus")) return true;
        return false;
    }

    public static boolean checkbuild(String name){
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

    public static boolean checkExtra(String action){
        switch (action){
            case "move": return true;
            case "build": return true;
            default: return false;
        }
    }

}
