# ISO2-2026-Grupo3C1.02-Testing-P1
Implementación del **Problema 1 (Gestión de Aparcamiento)** desarrollada por la **Pareja 1** del Grupo `3C1.02`.

## Decisiones de Diseño y Suposiciones
- **Validación en Constructor (`TicketEstancia`)**: Se comprueba que las fechas de entrada y salida no sean nulas y que la salida no sea anterior a la entrada, lanzando `TicketInvalidoException`. Si ambas fechas son iguales, la estancia es de 0 minutos (coste 0,00 €).
- **Reglas de Tarificación (`CalculadoraTarifaEstancia`)**:
  - Los primeros 30 minutos ($\le 30$) son gratuitos (0,00 €).
  - A partir del minuto 31 se factura a 0,05 €/minuto.
  - **Tope máximo diario (20,00 €)**: Por cada bloque completo de 24 horas (1.440 minutos) se aplica el tope de 20,00 €. Para estancias inferiores a 24 horas se descuentan los 30 minutos gratuitos y se aplica el límite máximo de 20,00 €.
- **Desacoplamiento UI / Dominio**: La lógica de negocio (`model`, `service`, `exception`) está completamente separada de la interfaz por línea de comandos (`ui.ConsolaParking`) mediante la interfaz `ICalculadoraTarifa`.
