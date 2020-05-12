package it.polimi.ingsw.PSP33.utils.observable;

import it.polimi.ingsw.PSP33.events.toServer.VCEvent;

import java.util.ArrayList;
import java.util.List;

public class Listened {

    private final List<Listener> listeners = new ArrayList<>();

    public void addListener(Listener listener) {
        synchronized (listeners) {
            listeners.add(listener);
        }
    }

    public void removeListener(Listener listener) {
        synchronized (listeners) {
            listeners.remove(listener);
        }
    }

    public void notifyListener(VCEvent vcEvent) {
        synchronized (listeners) {
            for (Listener listener : listeners) {
                listener.didReceiveMessage(vcEvent);
            }
        }
    }
}
