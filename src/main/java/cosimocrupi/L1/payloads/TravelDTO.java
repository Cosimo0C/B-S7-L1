package cosimocrupi.L1.payloads;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record TravelDTO(
        @NotEmpty(message = "La destinazione è obbligatoria!")
        @Size(min=4, message = "La destinazione deve essere lunga almeno 4 caratteri!")
        String destination,
        @FutureOrPresent(message = "La data deve essere minimo di oggi")
        LocalDate date,
        @NotEmpty(message = "Lo stato del viaggio è obbligatorio!")
        @Size(min = 10, message = "Deve essere minimo di 10 caratteri per definire se sia 'completato' o 'in programma'!")
        String stateTravel,
        @NotEmpty(message = "La prenotazione è obbligatoria!")
        String reservationId
) {
}
