package it.polimi.ingsw.PSP33.utils.observable;

import java.util.ArrayList;
import java.util.List;

/**
 * Part of the full implementation of the pattern observable-observer, used for the pattern MVC
 *
 * */
public class Observable<T> {

    private final List<Observer<T>> observers = new ArrayList<>();

    public void addObserver(Observer<T> observer) {
        synchronized (observers) {
            observers.add(observer);
        }
    }

    public void removeObserver(Observer<T> observer) {
        synchronized (observers) {
            observers.remove(observer);
        }
    }

    public void notifyObservers(T message) {
        synchronized (observers) {
            for (Observer<T> observer : observers) {
                observer.update(message);
            }
        }
    }
}