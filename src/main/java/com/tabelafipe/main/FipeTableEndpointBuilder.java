package com.tabelafipe.main;

public final class FipeTableEndpointBuilder {

    public final String ENDPOINT_BASE = "https://parallelum.com.br/fipe/api/v1/";
    private String typeOfVehicle = "";
    private String brandCode = "";
    private String vehicleModelCode = "";

    public FipeTableEndpointBuilder() {}

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


    public void setTypeOfVehicle(String typeOfVehicle) {
        this.typeOfVehicle = typeOfVehicle;
    }


    public void setBrandCode(String brandCode) {
        this.brandCode = brandCode;
    }


    public void setVehicleModelCode(String vehicleModelCode) {
        this.vehicleModelCode = vehicleModelCode;
    }
}
