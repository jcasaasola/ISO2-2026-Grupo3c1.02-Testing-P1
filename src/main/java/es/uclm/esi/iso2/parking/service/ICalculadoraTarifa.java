package es.uclm.esi.iso2.parking.service;

import es.uclm.esi.iso2.parking.exception.TicketInvalidoException;
import es.uclm.esi.iso2.parking.model.TicketEstancia;
import java.time.LocalDateTime;

public interface ICalculadoraTarifa {
    double calcularCoste(TicketEstancia ticket) throws TicketInvalidoException;
    double calcularCoste(LocalDateTime entrada, LocalDateTime salida) throws TicketInvalidoException;
}
