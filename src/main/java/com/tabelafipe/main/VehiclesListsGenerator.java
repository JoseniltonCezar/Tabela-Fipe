package com.tabelafipe.main;

import com.tabelafipe.model.VehicleBrand;
import com.tabelafipe.model.VehicleInfo;
import com.tabelafipe.model.VehicleModel;
import com.tabelafipe.model.VehicleYear;
import com.tabelafipe.services.ApiRequest;
import com.tabelafipe.services.JsonToObjectConverter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

import static com.tabelafipe.main.ListHelperMethods.sortListBy;

public class VehiclesListsGenerator {

    private static final FipeTableEndpointBuilder fipeTableEndpointBuilder = new FipeTableEndpointBuilder();

    public static List<VehicleBrand> generateListOfVehicleBrandsByTypeOfVehicle(String typeOfVehicle) {
        fipeTableEndpointBuilder.setTypeOfVehicle(typeOfVehicle);
        String vehicleBrandsEndpoint = fipeTableEndpointBuilder.getVehicleBrandsEndpointAccordingToTypeOfVehicle();
        String vehicleBrandsJson = ApiRequest.requestToApi(vehicleBrandsEndpoint);
        List<VehicleBrand> vehicleBrands = JsonToObjectConverter.convertJsonToListOfObjects(vehicleBrandsJson, VehicleBrand.class);
        sortListBy(vehicleBrands, Comparator.comparing(VehicleBrand::name));
        return  vehicleBrands;
    }

    public static List<VehicleModel> generateListOfVehicleModelsByBrandCode(String brandCode) {
        fipeTableEndpointBuilder.setBrandCode(brandCode);
        String vehicleModelsEndpoint = fipeTableEndpointBuilder.getVehicleModelsEndpointAccordingToBrandCode();
        String vehicleModelsJson = ApiRequest.requestToApi(vehicleModelsEndpoint);
        List<VehicleModel> vehicleModels = JsonToObjectConverter.convertJsonToListOfObjects(vehicleModelsJson, VehicleModel.class);
        sortListBy(vehicleModels, Comparator.comparing(VehicleModel::name));
        return vehicleModels;
    }

    public static List<VehicleModel> filterListOfVehicleModelsAccordingToVehicleName(List<VehicleModel> vehicleModels, String userVehicleName) {
        return vehicleModels.stream().filter(vehicleModel -> vehicleModel.name().toLowerCase().contains(userVehicleName.toLowerCase())).toList();
    }

    public static List<VehicleYear> generateListOfVehicleModelsYearsByVehicleModelCode(String vehicleModelCode) {
        fipeTableEndpointBuilder.setVehicleModelCode(vehicleModelCode);
        String vehicleModelsYearsEndpoint = fipeTableEndpointBuilder.getVehicleModelsYearsEndpointAccordingToVehicleModelCode();
        String vehicleModelsYearsJson = ApiRequest.requestToApi(vehicleModelsYearsEndpoint);
        List<VehicleYear> vehicleModelsYear = JsonToObjectConverter.convertJsonToListOfObjects(vehicleModelsYearsJson, VehicleYear.class);
        sortListBy(vehicleModelsYear, Comparator.comparing(VehicleYear::name));
        return vehicleModelsYear;
    }

    public static List<VehicleInfo> generateListOfVehiclesInfoForAllModelYears(List<VehicleYear> vehicleYears) {
        String vehicleInfo;
        List<VehicleInfo> vehiclesInfo = new ArrayList<>();
        for (VehicleYear vehicleYear : vehicleYears) {
            vehicleInfo = ApiRequest.requestToApi(fipeTableEndpointBuilder.getVehicleInfoEndpointAccordingToVehicleModelYear(vehicleYear.code()));
            vehiclesInfo.add(JsonToObjectConverter.convertJsonToObject(vehicleInfo, VehicleInfo.class));
        }
        return vehiclesInfo;
    }
}
