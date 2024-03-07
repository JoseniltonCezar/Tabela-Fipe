package com.tabelafipe.main;

import java.util.Scanner;

public class Menu {

    private static final Scanner sc = new Scanner(System.in);

    public static void printInitialMenu(){
        System.out.println("""
                **** OPTIONS ****
                     Car     \s
                     Truck   \s
                     Bike    \s
                """);
    }

    public static String askForTypeOfVehicle() {
        System.out.print("Write the type of vehicle you want to search for: ");
        return sc.nextLine();
    }

    public static String askForBrandCode() {
        System.out.print("Write the brand code to filter the models by brand: ");
        return sc.nextLine();
    }

    public static String askForVehicleName() {
        System.out.print("Type the vehicle's name or a part of it to filter its models: ");
        return sc.nextLine();
    }

    public static String askForVehicleModelCode() {
        System.out.print("Type the vehicle's model code to get the vehicle's details: ");
        return sc.nextLine();
    }
}
