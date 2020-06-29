package it.polimi.ingsw.PSP33.utils.observable;

import it.polimi.ingsw.PSP33.events.to_server.VCEvent;

public interface Listener {
    void didReceiveMessage(VCEvent vcEvent);
}
