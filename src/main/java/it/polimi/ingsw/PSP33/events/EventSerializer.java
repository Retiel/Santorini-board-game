package it.polimi.ingsw.PSP33.events;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import it.polimi.ingsw.PSP33.events.to_client.CCEvent;
import it.polimi.ingsw.PSP33.events.to_client.MVEvent;
import it.polimi.ingsw.PSP33.events.to_server.SCEvent;
import it.polimi.ingsw.PSP33.events.to_server.VCEvent;

/**
 * Custom serializer used for all kind of events
 */
public class EventSerializer {

    /**
     * Singleton instance
     */
    private static final EventSerializer instance = new EventSerializer();

    /**
     * GsonBuilder for mv events
     */
    private final Gson mvGson;

    /**
     * GsonBuilder for vc events
     */
    private final Gson vcGson;

    /**
     * GsonBuilder for cc events
     */
    private final Gson ccGson;

    /**
     * GsonBuilder for sc events
     */
    private final Gson scGson;

    private EventSerializer() {
        mvGson = new GsonBuilder().registerTypeAdapter(MVEvent.class, new EventAdapter<MVEvent>()).create();
        vcGson = new GsonBuilder().registerTypeAdapter(VCEvent.class, new EventAdapter<VCEvent>()).create();

        ccGson = new GsonBuilder().registerTypeAdapter(CCEvent.class, new EventAdapter<CCEvent>()).create();
        scGson = new GsonBuilder().registerTypeAdapter(SCEvent.class, new EventAdapter<SCEvent>()).create();
    }


    public static EventSerializer getInstance() {
        return instance;
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


    public String serializeCC(CCEvent ccEvent) {
        return ccGson.toJson(ccEvent, CCEvent.class);
    }

    public CCEvent deserializeCC(String ccJson) {
        return ccGson.fromJson(ccJson, CCEvent.class);
    }

    public String serializeSC(SCEvent scEvent) {
        return scGson.toJson(scEvent, SCEvent.class);
    }

    public SCEvent deserializeSC(String scJson) {
        return scGson.fromJson(scJson, SCEvent.class);
    }
}
