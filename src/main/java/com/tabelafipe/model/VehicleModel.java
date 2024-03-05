package com.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record VehicleModel (@JsonAlias("codigo") String cod, @JsonAlias("nome") String name){
}
