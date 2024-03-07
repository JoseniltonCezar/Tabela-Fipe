package com.tabelafipe.main;

import com.tabelafipe.model.*;
import com.tabelafipe.services.ApiRequest;
import com.tabelafipe.services.JsonToObjectConverter;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import static com.tabelafipe.main.ListHelperMethods.sortListBy;

public class Menu {

    private static final Scanner sc = new Scanner(System.in);

    public static void printInitialMenu(){
        System.out.println("""
                **** OPÇÕES ****
                     Carro     \s
                     Caminhão  \s
                     Moto      \s
                """);
    }

    public static String askForTypeOfVehicle() {
        System.out.print("Digite uma das opções de tipo de veículo: ");
        return sc.nextLine();
    }

    public static String askForBrandCode() {
        System.out.print("Informe o código da marca para consultar: ");
        return sc.nextLine();
    }

    public static String askForVehicleName() {
        System.out.print("Digite um trecho do nome do veículo para consulta: ");
        return sc.nextLine();
    }

    public static String askForVehicleModelCode() {
        System.out.println("Digite o código do modelo do veículo para consultar valores:");
        return sc.nextLine();
    }
}
