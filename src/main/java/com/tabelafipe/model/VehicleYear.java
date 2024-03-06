package com.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record VehicleYear(@JsonAlias("codigo") String code, @JsonAlias("nome") String name) {
}
