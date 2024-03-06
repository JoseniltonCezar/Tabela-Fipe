package com.tabelafipe.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.List;

public class JsonToObjectConverter {

    static ObjectMapper mapper = new ObjectMapper();

    static public <T> T convertJsonToObject(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    static public <T> List<T> convertJsonToListOfObjects(String json, Class<T> classe) {
        CollectionType returnJsonAsList = mapper.getTypeFactory().constructCollectionType(List.class, classe);

        try{
            return mapper.readValue(json, returnJsonAsList);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    static public <T> List<T> returnJsonListDirectlyAsArray(String json, Class<T> classe) {

        try {
            JsonNode node = mapper.readTree(json);
            JsonNode arrayNode = node.get("modelos");
            System.out.println(arrayNode.toString());
            return convertJsonToListOfObjects(arrayNode.toString(), classe);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }

    }
}
