package utils;

/**
 * Interface of the pattern observable-observer, used for the pattern MVC
 * */
public interface Observer<T> {

    void update(T message);
}
