package it.polimi.ingsw.PSP33.utils.patterns.observable;

import it.polimi.ingsw.PSP33.events.toServer.VCEvent;

public interface Listener {
    void didReceiveMessage(VCEvent vcEvent);
}
