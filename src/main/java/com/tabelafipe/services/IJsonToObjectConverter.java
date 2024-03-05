package com.tabelafipe.services;

import java.util.List;

public interface IJsonToObjectConverter {
    <T> T convertJsonToObject(String json, Class<T> classe);

    <T> List<T> convertJsonToListOfObjects(String json, Class<T> classe);
}
