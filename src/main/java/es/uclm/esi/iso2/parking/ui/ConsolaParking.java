package es.uclm.esi.iso2.parking.ui;

import es.uclm.esi.iso2.parking.exception.TicketInvalidoException;
import es.uclm.esi.iso2.parking.service.ICalculadoraTarifa;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class ConsolaParking {
    private static final DateTimeFormatter FORMATO_FECHA = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
    private final Scanner scanner;
    private final ICalculadoraTarifa calculadora;

    public ConsolaParking(Scanner scanner, ICalculadoraTarifa calculadora) {
        this.scanner = scanner;
        this.calculadora = calculadora;
    }

    public void iniciar() {
        mostrarMensaje("=== SISTEMA DE GESTIÓN DE APARCAMIENTO ===");
        try {
            LocalDateTime entrada = leerFechaHora("Introduzca fecha y hora de entrada (dd/MM/yyyy HH:mm): ");
            LocalDateTime salida = leerFechaHora("Introduzca fecha y hora de salida (dd/MM/yyyy HH:mm): ");

            double importe = calculadora.calcularCoste(entrada, salida);
            mostrarMensaje(String.format("El importe total de la estancia es: %.2f €", importe));
        } catch (TicketInvalidoException e) {
            mostrarError("Error en los datos del ticket: " + e.getMessage());
        } catch (DateTimeParseException e) {
            mostrarError("Formato de fecha/hora inválido. Use exactamente dd/MM/yyyy HH:mm.");
        }
    }

    public LocalDateTime leerFechaHora(String mensaje) throws DateTimeParseException {
        System.out.print(mensaje);
        String entradaTexto = scanner.nextLine().trim();
        return LocalDateTime.parse(entradaTexto, FORMATO_FECHA);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarError(String mensaje) {
        System.err.println(mensaje);
    }
}
