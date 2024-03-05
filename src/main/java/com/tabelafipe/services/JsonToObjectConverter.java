package com.tabelafipe.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;

import java.util.Collections;
import java.util.List;

public class JsonToObjectConverter implements IJsonToObjectConverter{

    ObjectMapper mapper = new ObjectMapper();

    @Override
    public <T> T convertJsonToObject(String json, Class<T> classe) {
        try {
            return mapper.readValue(json, classe);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public <T> List<T> convertJsonToListOfObjects(String json, Class<T> classe) {
        CollectionType returnJsonAsList = mapper.getTypeFactory().constructCollectionType(List.class, classe);

        try{
            return mapper.readValue(json, returnJsonAsList);
        } catch (JsonMappingException e) {
            throw new RuntimeException(e);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }
}
