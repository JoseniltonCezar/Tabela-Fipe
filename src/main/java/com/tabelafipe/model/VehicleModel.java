package com.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;

public record VehicleModel (@JsonAlias("codigo") String code, @JsonAlias("nome") String name){
    @Override
    public String toString() {
        return "Code: " + this.code + " | Name: " + this.name;
    }
}
