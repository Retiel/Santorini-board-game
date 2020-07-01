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
     * GsonBuilder for model-view events
     */
    private final Gson mvGson;

    /**
     * GsonBuilder for view-controller events
     */
    private final Gson vcGson;

    /**
     * GsonBuilder for client-connection events
     */
    private final Gson ccGson;

    /**
     * GsonBuilder for server-connection events
     */
    private final Gson scGson;

    private EventSerializer() {
        mvGson = new GsonBuilder().registerTypeAdapter(MVEvent.class, new EventAdapter<MVEvent>()).create();
        vcGson = new GsonBuilder().registerTypeAdapter(VCEvent.class, new EventAdapter<VCEvent>()).create();
        ccGson = new GsonBuilder().registerTypeAdapter(CCEvent.class, new EventAdapter<CCEvent>()).create();
        scGson = new GsonBuilder().registerTypeAdapter(SCEvent.class, new EventAdapter<SCEvent>()).create();
    }

    /**
     * Method to get the singleton instance
     * @return EventSerializer unique instance
     */
    public static EventSerializer getInstance() {
        return instance;
    }

    /**
     * Method used to serialize a model-view event
     * @param mvEvent model-view event
     * @return model-view event's json
     */
    public String serializeMV(MVEvent mvEvent) {
        return mvGson.toJson(mvEvent, MVEvent.class);
    }

    /**
     * Method used to deserialize a model-view event's json
     * @param mvJson model-view event's json
     * @return model-view event
     */
    public MVEvent deserializeMV(String mvJson) {
        return mvGson.fromJson(mvJson, MVEvent.class);
    }

    /**
     * Method used to serialize a view-controller event
     * @param vcEvent view-controller event
     * @return view-controller event's json
     */
    public String serializeVC(VCEvent vcEvent) {
        return vcGson.toJson(vcEvent, VCEvent.class);
    }

    /**
     * Method used to deserialize a view-controller event
     * @param vcJson view-controller event's json
     * @return view-controller event
     */
    public VCEvent deserializeVC(String vcJson) {
        return vcGson.fromJson(vcJson, VCEvent.class);
    }

    /**
     * Method used to serialize a client-connection event
     * @param ccEvent client-connection event
     * @return client-connection event's json
     */
    public String serializeCC(CCEvent ccEvent) {
        return ccGson.toJson(ccEvent, CCEvent.class);
    }

    /**
     * Method used to deserialize a client-connection event
     * @param ccJson client-connection event's json
     * @return client-connection event
     */
    public CCEvent deserializeCC(String ccJson) {
        return ccGson.fromJson(ccJson, CCEvent.class);
    }

    /**
     * Method used to serialize a server-connection event
     * @param scEvent server-connection event
     * @return server-connection event's json
     */
    public String serializeSC(SCEvent scEvent) {
        return scGson.toJson(scEvent, SCEvent.class);
    }

    /**
     * Method used to deserialize a server-connection event
     * @param scJson server-connection event's json
     * @return server-connection event
     */
    public SCEvent deserializeSC(String scJson) {
        return scGson.fromJson(scJson, SCEvent.class);
    }
}
