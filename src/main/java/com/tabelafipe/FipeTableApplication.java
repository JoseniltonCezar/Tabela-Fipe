package com.tabelafipe;

import com.tabelafipe.model.VehicleBrand;
import com.tabelafipe.model.VehicleInfo;
import com.tabelafipe.model.VehicleModel;
import com.tabelafipe.model.VehicleYear;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

import static com.tabelafipe.main.ListHelperMethods.printList;
import static com.tabelafipe.main.Menu.*;
import static com.tabelafipe.main.VehiclesListsGenerator.*;

@SpringBootApplication
public class FipeTableApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(FipeTableApplication.class, args);
	}

	@Override
	public void run(String... args) {
		printInitialMenu();

		String typeOfVehicle = askForTypeOfVehicle();
		List<VehicleBrand> vehicleBrands = generateListOfVehicleBrandsByTypeOfVehicle(typeOfVehicle);
		printList(vehicleBrands);

		String brandCode = askForBrandCode();
		List<VehicleModel> vehicleModels = generateListOfVehicleModelsByBrandCode(brandCode);
		printList(vehicleModels);

		String vehicleName = askForVehicleName();
		vehicleModels = filterListOfVehicleModelsAccordingToVehicleName(vehicleModels, vehicleName);
		printList(vehicleModels);

		String vehicleModelCode = askForVehicleModelCode();
		List<VehicleYear> vehicleYears = generateListOfVehicleModelsYearsByVehicleModelCode(vehicleModelCode);
		List<VehicleInfo> vehicleInfos = generateListOfVehiclesInfoForAllModelYears(vehicleYears);
		printList(vehicleInfos);
	}
}
