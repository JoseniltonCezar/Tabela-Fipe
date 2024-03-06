package com.tabelafipe.main;

import com.tabelafipe.model.*;
import com.tabelafipe.services.ApiRequest;
import com.tabelafipe.services.JsonToObjectConverter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

public class Menu {

    private final Scanner sc = new Scanner(System.in);

    public void exibeMenu(){
        System.out.println("""
                **** OPÇÕES ****
                     Carro     \s
                     Caminhão  \s
                     Moto      \s
                """);

        String typeOfVehicle = askForTypeOfVehicle();

        List<VehicleBrand> vehicleBrands = generateListOfVehicleBrands(typeOfVehicle);
        sortListBy(vehicleBrands, Comparator.comparing(VehicleBrand::name));
        printList(vehicleBrands);

        String brandCode = askForBrandCode();

        List<VehicleModel> vehicleModels = generateListOfVehicleModels(typeOfVehicle, brandCode);
        sortListBy(vehicleModels, Comparator.comparing(VehicleModel::name));
        printList(vehicleModels);

        String vehicleName = askForVehicleName();
        vehicleModels = filterListOfBrandVehicleModelsAccordingToUserVehicle(vehicleModels, vehicleName);
        printList(vehicleModels);

        String vehicleModelCode = askForVehicleModelCode();
        List<VehicleYear> vehicleYears = generateListOfVehicleModelsYears(typeOfVehicle, brandCode, vehicleModelCode);
        sortListBy(vehicleYears, Comparator.comparing(VehicleYear::name));

        List<VehicleInfo> vehicleInfos = getVehicleInfoForAllModelYears(vehicleYears, typeOfVehicle, brandCode, vehicleModelCode);
        printList(vehicleInfos);

    }

    private String askForTypeOfVehicle() {
        System.out.print("Digite uma das opções de tipo de veículo: ");
        return sc.nextLine();
    }

    private String getVehicleBrandsEndpointAccordingToTypeOfVehicle(String typeOfVehicle) {
        String endpoint = "";
        String ENDPOINT_BASE = "https://parallelum.com.br/fipe/api/v1/";
        if(typeOfVehicle.toLowerCase().contains("car".toLowerCase()))
            endpoint = ENDPOINT_BASE + "carros/marcas";
        else if (typeOfVehicle.toLowerCase().contains("camin".toLowerCase()))
            endpoint = ENDPOINT_BASE + "caminhoes/marcas";
        else if (typeOfVehicle.toLowerCase().contains("mot".toLowerCase()))
            endpoint = ENDPOINT_BASE + "motos/marcas";

        return endpoint;
    }

    private List<VehicleBrand> generateListOfVehicleBrands(String typeOfVehicle) {
        String vehicleBrandsEndpoint = getVehicleBrandsEndpointAccordingToTypeOfVehicle(typeOfVehicle);
        String vehicleBrandsJson = ApiRequest.requestToApi(vehicleBrandsEndpoint);
        return JsonToObjectConverter.convertJsonToListOfObjects(vehicleBrandsJson, VehicleBrand.class);
    }

    private <T> void sortListBy(List<T> list, Comparator<T> comparator) {
        list.sort(comparator);
    }

    private <T> void printList(List<T> list) {
        list.forEach(System.out::println);
    }

    private String askForBrandCode() {
        System.out.print("Informe o código da marca para consultar: ");
        return sc.nextLine();
    }

    private List<VehicleModel> generateListOfVehicleModels(String typeOfVehicle, String brandCode) {
        String vehicleModelsEndpoint = getVehicleModelsEndpointAccordingToBrandCode(typeOfVehicle, brandCode);
        String vehicleModelsJson = ApiRequest.requestToApi(vehicleModelsEndpoint);
        return JsonToObjectConverter.returnJsonListDirectlyAsArray(vehicleModelsJson, VehicleModel.class);
    }

    private String getVehicleModelsEndpointAccordingToBrandCode(String typeOfVehicle, String brandCode) {
        return getVehicleBrandsEndpointAccordingToTypeOfVehicle(typeOfVehicle) + "/" + brandCode + "/modelos/";
    }

    private String askForVehicleName() {
        System.out.print("Digite um trecho do nome do veículo para consulta: ");
        return sc.nextLine();
    }

    private List<VehicleModel> filterListOfBrandVehicleModelsAccordingToUserVehicle(List<VehicleModel> vehicleModels, String userVehicleName) {
       return vehicleModels.stream().filter(vehicleModel -> vehicleModel.name().toLowerCase().contains(userVehicleName.toLowerCase())).toList();
    }

    private String askForVehicleModelCode() {
        System.out.println("Digite o código do modelo do veículo para consultar valores:");
        return sc.nextLine();
    }

    private List<VehicleYear> generateListOfVehicleModelsYears(String typeOfVehicle, String brandCode, String vehicleModelCode) {
        String vehicleModelsYearsEndpoint = getVehicleModelsYearsEndpointAccordingToVehicleModelCode(typeOfVehicle, brandCode, vehicleModelCode);
        String vehicleModelsYearsJson = ApiRequest.requestToApi(vehicleModelsYearsEndpoint);
        return JsonToObjectConverter.convertJsonToListOfObjects(vehicleModelsYearsJson, VehicleYear.class);
    }

    private String getVehicleModelsYearsEndpointAccordingToVehicleModelCode(String typeOfVehicle, String brandCode, String vehicleModelCode) {
        return getVehicleModelsEndpointAccordingToBrandCode(typeOfVehicle, brandCode) + vehicleModelCode + "/anos/";
    }

    private List<VehicleInfo> getVehicleInfoForAllModelYears(List<VehicleYear> vehicleYears, String typeOfVehicle, String brandCode, String vehicleModelCode) {
        String vehicleInfo;
        List<VehicleInfo> vehiclesInfo = new ArrayList<>();
        for (VehicleYear vehicleYear : vehicleYears) {
            vehicleInfo = ApiRequest.requestToApi(getVehicleModelsYearsEndpointAccordingToVehicleModelCode(typeOfVehicle, brandCode, vehicleModelCode) + vehicleYear.code());
            vehiclesInfo.add(JsonToObjectConverter.convertJsonToObject(vehicleInfo, VehicleInfo.class));
        }
        return vehiclesInfo;
    }
}
