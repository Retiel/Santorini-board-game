package it.polimi.ingsw.PSP33.utils.observable;

import it.polimi.ingsw.PSP33.events.to_server.VCEvent;

/**
 * Interface of the pattern observable-observer, used by GameHandler to get view-controller events from each ClientHandlers
 */
public interface Listener {

    /**
     * Update method of the pattern observable-observer
     * @param vcEvent view-controller event
     */
    void didReceiveMessage(VCEvent vcEvent);
}
