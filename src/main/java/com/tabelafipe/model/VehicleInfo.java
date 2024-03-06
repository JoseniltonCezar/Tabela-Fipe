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
        return "Modelo: " + this.modelo + " | Marca: " + this.marca + " | Valor: " + this.valor + " | Ano: " + this.ano + " | Combustível: " + this.combustivel;
    }
}
