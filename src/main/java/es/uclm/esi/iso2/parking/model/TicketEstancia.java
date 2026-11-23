package es.uclm.esi.iso2.parking.model;

import es.uclm.esi.iso2.parking.exception.TicketInvalidoException;
import java.time.Duration;
import java.time.LocalDateTime;

public class TicketEstancia {
    private final LocalDateTime fechaEntrada;
    private final LocalDateTime fechaSalida;

    public TicketEstancia(LocalDateTime fechaEntrada, LocalDateTime fechaSalida) throws TicketInvalidoException {
        if (fechaEntrada == null || fechaSalida == null) {
            throw new TicketInvalidoException("Las fechas de entrada y salida no pueden ser nulas.");
        }
        if (fechaSalida.isBefore(fechaEntrada)) {
            throw new TicketInvalidoException("La fecha de salida no puede ser anterior a la fecha de entrada.");
        }
        this.fechaEntrada = fechaEntrada;
        this.fechaSalida = fechaSalida;
    }

    public LocalDateTime getFechaEntrada() {
        return fechaEntrada;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public long getDuracionEnMinutos() {
        return Duration.between(fechaEntrada, fechaSalida).toMinutes();
    }
}
