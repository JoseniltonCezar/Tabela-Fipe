package com.tabelafipe.main;

import com.tabelafipe.model.*;
import com.tabelafipe.services.ConsultaApi;
import com.tabelafipe.services.JsonToObjectConverter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final Scanner sc = new Scanner(System.in);
    private final String ENDPOINT_BASE = "https://parallelum.com.br/fipe/api/v1/";

    public void exibeMenu(){
        JsonToObjectConverter converter = new JsonToObjectConverter();

        System.out.println("**** OPÇÕES ****\n" +
                           "     Carro      \n" +
                           "    Caminhão    \n" +
                           "     Moto       \n");

        System.out.println("Digite uma das opções para consultar valores:");
        String opcao = sc.nextLine();

        String endpoint = "";
        if(opcao.toLowerCase().contains("car".toLowerCase())){
            endpoint = ENDPOINT_BASE + "carros/marcas";
        } else if (opcao.toLowerCase().contains("camin".toLowerCase())) {
            endpoint = ENDPOINT_BASE + "caminhoes/marcas";
        } else if (opcao.toLowerCase().contains("mot".toLowerCase())) {
            endpoint = ENDPOINT_BASE + "motos/marcas";
        }

        String json = ConsultaApi.consultaApi(endpoint);
        System.out.println(json);

        List<VehicleBrand> vehicleBrands = converter.convertJsonToListOfObjects(json, VehicleBrand.class);

        vehicleBrands.stream()
                .sorted(Comparator.comparing(VehicleBrand::name))
                .forEach(v -> System.out.println("Code: " + v.cod() + " Brand: " + v.name()));

        System.out.println("Informe o código da marca para consultar:");
        int op = sc.nextInt();
        sc.nextLine();

        endpoint = endpoint + "/" + op + "/modelos";

        json = ConsultaApi.consultaApi(endpoint);

        Models model = converter.convertJsonToObject(json, Models.class);

        model.models().stream()
                .sorted(Comparator.comparing(VehicleModel::name))
                .forEach(m -> System.out.println("Code: " + m.cod() + " Model: " + m.name()));

        System.out.println("Digite um trecho do nome do veículo para consulta:");
        String option = sc.nextLine();

        model.models().stream()
                .filter(m -> m.name().toLowerCase().contains(option.toLowerCase()))
                .forEach(m -> System.out.println("Code: " + m.cod() + " Model: " + m.name()));

        System.out.println("Digite o código do modelo para consultar valores:");
        op = sc.nextInt();
        sc.nextLine();

        endpoint = endpoint + "/" + op + "/anos";
        json = ConsultaApi.consultaApi(endpoint);

        List<VehicleYear> years = converter.convertJsonToListOfObjects(json, VehicleYear.class);

        List<VehicleInfo> vehicleInfos = new ArrayList<>();

        for (int i = 0; i < years.size(); i++) {
            json = ConsultaApi.consultaApi(endpoint + "/" + years.get(i).cod());

            vehicleInfos.add(converter.convertJsonToObject(json, VehicleInfo.class));
        }

        vehicleInfos.forEach(System.out::println);

    }
}
