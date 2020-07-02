package it.polimi.ingsw.PSP33.utils.observable;

/**
 * Interface of the pattern observable-observer, used for the pattern MVC
 */
public interface Observer<T> {

    /**
     * Update method of the pattern observable-observer
     * @param message event (view-controller or model-view event)
     */
    void update(T message);
}
