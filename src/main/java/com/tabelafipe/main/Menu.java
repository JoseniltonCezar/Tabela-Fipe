package com.tabelafipe.main;

import java.util.Scanner;

public class Menu {

    private static final Scanner sc = new Scanner(System.in);

    public static void printInitialMenu(){
        System.out.println("""
                **** OPTIONS ****
                    - Car
                    - Truck
                    - Bike""");
    }

    public static String askForTypeOfVehicle() {
        System.out.print("\nWrite the type of vehicle you want to search for: ");
        return sc.nextLine();
    }

    public static String askForBrandCode() {
        System.out.print("\nWrite the brand code to filter the models by brand: ");
        return sc.nextLine();
    }

    public static String askForVehicleName() {
        System.out.print("\nType the vehicle's name or a part of it to filter its models: ");
        return sc.nextLine();
    }

    public static String askForVehicleModelCode() {
        System.out.print("\nType the vehicle's model code to get the vehicle's details: ");
        return sc.nextLine();
    }
}
