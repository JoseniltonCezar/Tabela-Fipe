package com.tabelafipe.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record VehicleInfo(@JsonAlias("Valor") String valor,
                          @JsonAlias("Marca") String marca,
                          @JsonAlias("Modelo") String modelo,
                          @JsonAlias("AnoModelo") String ano,
                          @JsonAlias("Combustivel") String combustivel) {

    @Override
    public String toString() {
        return "Model: " + this.modelo + " | Brand: " + this.marca + " | Price: " + this.valor + " | Year: " + this.ano + " | Fuel Type: " + this.combustivel;
    }
}
