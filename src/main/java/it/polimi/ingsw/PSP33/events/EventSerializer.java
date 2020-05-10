package it.polimi.ingsw.PSP33.events;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.PSP33.events.toClient.MVEvent;
import it.polimi.ingsw.PSP33.events.toServer.VCEvent;

public class EventSerializer {

    private final Gson mvGson;
    private final Gson vcGson;

    public EventSerializer() {
        mvGson = new GsonBuilder().registerTypeAdapter(MVEvent.class, new EventAdapter<MVEvent>()).create();
        vcGson = new GsonBuilder().registerTypeAdapter(VCEvent.class, new EventAdapter<VCEvent>()).create();
    }

    public String serializeMV(MVEvent mvEvent) {
        return mvGson.toJson(mvEvent, MVEvent.class);
    }

    public MVEvent deserializeMV(String mvJson) {
        return mvGson.fromJson(mvJson, MVEvent.class);
    }

    public String serializeVC(VCEvent vcEvent) {
        return vcGson.toJson(vcEvent, VCEvent.class);
    }

    public VCEvent deserializeVC(String vcJson) {
        return vcGson.fromJson(vcJson, VCEvent.class);
    }
}
