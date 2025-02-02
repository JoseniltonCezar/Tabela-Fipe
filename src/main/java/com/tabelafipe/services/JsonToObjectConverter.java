package com.tabelafipe.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class JsonToObjectConverter {

    private static final ObjectMapper mapper = new ObjectMapper();

    public static <T> T convertJsonToObject(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    public static <T> List<T> convertJsonToListOfObjects(String json, Class<T> classe) {
        CollectionType returnJsonAsList = mapper.getTypeFactory().constructCollectionType(List.class, classe);
        try{
            json = getJsonArrayContentIfParentElementIsAnObject(json);
            return mapper.readValue(json, returnJsonAsList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    private static String getJsonArrayContentIfParentElementIsAnObject(String json) throws JsonProcessingException {
            JsonNode jsonNode = mapper.readTree(json);
            if (jsonNode.isObject())
                return getJsonNodeFirstChildElement(jsonNode);
            return json;
    }

    private static String getJsonNodeFirstChildElement(JsonNode jsonNode) {
        return jsonNode.elements().next().toString();
    }
}
