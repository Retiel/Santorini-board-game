package controller.rules;


//singleton example
public class example {

    //create an object of SingleObject
    private static example instance = new example();

    //make the constructor private so that this class cannot be
    //instantiated
    private example(){}

    //Get the only object available
    public static example getInstance(){
        return instance;
    }

    public void showMessage(){
        System.out.println("Hello World!");
    }
}
