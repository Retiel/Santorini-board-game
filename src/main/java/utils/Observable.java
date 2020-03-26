package utils;

import message.MessageInterface;

import java.util.ArrayList;
import java.util.List;

public class Observable<T> {

    private final List<Observer<T>> observers = new ArrayList<>();

    public void addObserver(Observer observer) {
        synchronized (observers) {
            observers.add(observer);
        }
    }

    public void removeObserver(Observer observer) {
        synchronized (observers) {
            observers.remove(observer);
        }
    }

    public void notifyObservers(MessageInterface messageInterface) {
        synchronized (observers) {
            for (Observer observer : observers) {
                observer.update(messageInterface);
            }
        }
    }
}