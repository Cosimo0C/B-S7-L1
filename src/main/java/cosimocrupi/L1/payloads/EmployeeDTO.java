package cosimocrupi.L1.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public record EmployeeDTO(
        @NotEmpty(message="L'username è obbligatorio!")
        @Size(min = 8, message = "L'username deve essere minimo 8 caratteri!")
        String username,
        @NotEmpty(message = "Il nome è obbligatorio!")
        @Size(min = 3, max = 15, message = "Il nome deve essere compreso tra 3 e 15 caratteri")
        String name,
        @NotEmpty(message = "Il cognome è obbligatorio!")
        @Size(min = 3, max = 15, message = "Il cognome deve essere compreso tra 3 e 15 caratteri")
        String surname,
        @NotEmpty(message = "L'emil è obbligatorio!")
        @Email(message = "L'email inserita non è del formato corretto!")
        String email
) {
}
