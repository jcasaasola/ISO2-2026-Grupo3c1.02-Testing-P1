package es.uclm.esi.iso2.parking.service;

import es.uclm.esi.iso2.parking.exception.TicketInvalidoException;
import es.uclm.esi.iso2.parking.model.TicketEstancia;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDateTime;

public class CalculadoraTarifaEstancia implements ICalculadoraTarifa {

    public static final int MINUTOS_GRATIS = 30;
    public static final double PRECIO_POR_MINUTO = 0.05;
    public static final double TOPE_MAXIMO_DIARIO = 20.00;
    public static final long MINUTOS_POR_DIA = 1440;

    @Override
    public double calcularCoste(LocalDateTime entrada, LocalDateTime salida) throws TicketInvalidoException {
        TicketEstancia ticket = new TicketEstancia(entrada, salida);
        return calcularCoste(ticket);
    }

    @Override
    public double calcularCoste(TicketEstancia ticket) throws TicketInvalidoException {
        if (ticket == null) {
            throw new TicketInvalidoException("El ticket de estancia no puede ser nulo.");
        }

        long minutosTotales = ticket.getDuracionEnMinutos();

        if (minutosTotales <= MINUTOS_GRATIS) {
            return 0.0;
        }

        long diasCompletos = minutosTotales / MINUTOS_POR_DIA;
        long minutosRestantes = minutosTotales % MINUTOS_POR_DIA;

        double costeDiasCompletos = diasCompletos * TOPE_MAXIMO_DIARIO;
        double costeTramoRestante = 0.0;

        if (diasCompletos == 0) {
            long minutosFacturables = minutosRestantes - MINUTOS_GRATIS;
            costeTramoRestante = Math.min(minutosFacturables * PRECIO_POR_MINUTO, TOPE_MAXIMO_DIARIO);
        } else {
            costeTramoRestante = Math.min(minutosRestantes * PRECIO_POR_MINUTO, TOPE_MAXIMO_DIARIO);
        }

        double costeTotal = costeDiasCompletos + costeTramoRestante;
        return BigDecimal.valueOf(costeTotal).setScale(2, RoundingMode.HALF_UP).doubleValue();
    }
}
