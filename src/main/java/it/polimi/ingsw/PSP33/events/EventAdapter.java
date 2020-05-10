package it.polimi.ingsw.PSP33.events;

import com.google.gson.*;

import java.lang.reflect.Type;

public class EventAdapter<T> implements JsonSerializer<T>, JsonDeserializer<T> {

    private static final String CLASSNAME = "CLASSNAME";
    private static final String INSTANCE = "INSTANCE";

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

    public JsonElement serialize(T event, Type type, JsonSerializationContext jsonSerializationContext) {

        JsonObject retValue = new JsonObject();
        String className = event.getClass().getName();
        retValue.addProperty(CLASSNAME, className);
        JsonElement jsonElement = jsonSerializationContext.serialize(event);
        retValue.add(INSTANCE, jsonElement);
        return retValue;
    }
}
