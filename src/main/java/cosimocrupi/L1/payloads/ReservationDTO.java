package cosimocrupi.L1.payloads;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import java.time.LocalDate;

public record ReservationDTO(
        @NotEmpty(message = "La data di richiesta è obligatoria!")
        @FutureOrPresent(message = "La data si riferisce ad un gionro già passato!")
        LocalDate dateRequest,
        @Size(min = 10, max=100, message = "Le note o le preferenze devono essere comprese tra 10 e 100 caratteri!")
        String noteOrPreferences,
        @NotEmpty(message = "Il dipendete è obbligatorio!")
        String employeeId
) {
}
