package com.tabelafipe.main;

public final class FipeTableEndpointBuilder {

    public final String ENDPOINT_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private String typeOfVehicle = "";
    private String brandCode = "";
    private String vehicleModelCode = "";

    public FipeTableEndpointBuilder() {}

    public FipeTableEndpointBuilder(String typeOfVehicle) {
        this.typeOfVehicle = typeOfVehicle;
    }

    public FipeTableEndpointBuilder(String typeOfVehicle, String brandCode) {
        new FipeTableEndpointBuilder(typeOfVehicle);
        this.brandCode = brandCode;
    }

    public FipeTableEndpointBuilder(String typeOfVehicle, String brandCode, String vehicleModelCode) {
        new FipeTableEndpointBuilder(typeOfVehicle, brandCode);
        this.vehicleModelCode = vehicleModelCode;
    }

    String getVehicleBrandsEndpointAccordingToTypeOfVehicle() {
        if(this.typeOfVehicle.toLowerCase().contains("carro".toLowerCase()))
            return ENDPOINT_BASE + "carros/marcas";
        else if (this.typeOfVehicle.toLowerCase().contains("caminh".toLowerCase()))
            return ENDPOINT_BASE + "caminhoes/marcas";
        else if (this.typeOfVehicle.toLowerCase().contains("moto".toLowerCase()))
            return ENDPOINT_BASE + "motos/marcas";
        return "Invalid Endpoint";
    }

    String getVehicleModelsEndpointAccordingToBrandCode() {
        return getVehicleBrandsEndpointAccordingToTypeOfVehicle() + "/" + this.brandCode + "/modelos/";
    }

    String getVehicleModelsYearsEndpointAccordingToVehicleModelCode() {
        return getVehicleModelsEndpointAccordingToBrandCode() + this.vehicleModelCode + "/anos/";
    }

    String getVehicleInfoEndpointAccordingToVehicleModelYear(String vehicleYear) {
        return getVehicleModelsYearsEndpointAccordingToVehicleModelCode() + vehicleYear;
    }

    public String getTypeOfVehicle() {
        return typeOfVehicle;
    }

    public void setTypeOfVehicle(String typeOfVehicle) {
        this.typeOfVehicle = typeOfVehicle;
    }

    public String getBrandCode() {
        return brandCode;
    }

    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }

    public String getVehicleModelCode() {
        return vehicleModelCode;
    }

    public void setVehicleModelCode(String vehicleModelCode) {
        this.vehicleModelCode = vehicleModelCode;
    }
}
