package org.vult.crrfr.utils;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;

public class JsonUtils {

    public static String getSafeString(JsonObject obj, String memberName) {
        JsonElement element = obj.get(memberName);
        return (element != null && !element.isJsonNull()) ? element.getAsString() : "Na";
    }

    public static int getSafeInt(JsonObject obj, String memberName) {
        JsonElement element = obj.get(memberName);
        return (element != null && !element.isJsonNull()) ? element.getAsInt() : -1;
    }

    public static float getSafeFloat(JsonObject obj, String memberName) {
        JsonElement element = obj.get(memberName);
        try {
            return (element != null && !element.isJsonNull()) ? Float.parseFloat(element.getAsString()) : Float.NaN;
        } catch (NumberFormatException e) {
            return Float.NaN;
        }
    }

    public static double getSafeDouble(JsonObject obj, String memberName) {
        JsonElement element = obj.get(memberName);
        return (element != null && !element.isJsonNull()) ? element.getAsDouble() : Double.NaN;
    }
}
