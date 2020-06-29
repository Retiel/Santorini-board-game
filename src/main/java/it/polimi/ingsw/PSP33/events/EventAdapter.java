package it.polimi.ingsw.PSP33.events;

import com.google.gson.*;

import java.lang.reflect.Type;

/**
 * Custom generic adapter used by EventSerializer
 * @param <T> type of event
 */
public class EventAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {

    /**
     * String holding the class name of the event
     */
    private static final String CLASSNAME = "CLASSNAME";

    /**
     * String holding the json representing the event
     */
    private static final String INSTANCE = "INSTANCE";

    /**
     * Method used to deserialize json representing events, using class name to cast to the correct class
     * @param jsonElement json represing the serialized event
     * @param type type of event
     * @param jsonDeserializationContext deserialization context used by gson
     * @return event
     * @throws JsonParseException class not found
     */
    public T deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext)
            throws JsonParseException {
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        JsonPrimitive jsonPrimitive = (JsonPrimitive) jsonObject.get(CLASSNAME);
        String className = jsonPrimitive.getAsString();

        Class<?> klass;
        try{
            klass = Class.forName(className);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            throw new JsonParseException(e.getMessage());
        }

        return jsonDeserializationContext.deserialize(jsonObject.get(INSTANCE), klass);
    }

    /**
     * Method used to serialize events, saving the name of the event's class
     * @param event event
     * @param type type of event
     * @param jsonSerializationContext serialization context used by gson
     * @return string representing the event serialized
     */
    public JsonElement serialize(T event, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject retValue = new JsonObject();
        String className = event.getClass().getName();
        retValue.addProperty(CLASSNAME, className);
        JsonElement jsonElement = jsonSerializationContext.serialize(event);
        retValue.add(INSTANCE, jsonElement);
        return retValue;
    }
}
