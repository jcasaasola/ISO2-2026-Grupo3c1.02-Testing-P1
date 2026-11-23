package es.uclm.esi.iso2.parking.ui;

import es.uclm.esi.iso2.parking.service.CalculadoraTarifaEstancia;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            ConsolaParking consola = new ConsolaParking(scanner, new CalculadoraTarifaEstancia());
            consola.iniciar();
        }
    }
}
