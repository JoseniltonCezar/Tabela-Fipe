package com.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record VehicleBrand(@JsonAlias("codigo") String cod, @JsonAlias("nome") String name) {}
