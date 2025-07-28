package cosimocrupi.L1.exceptions;

import java.util.UUID;

public class NotFoundException extends RuntimeException {
    public NotFoundException(UUID id) {
        super("La risorsa con questo id " + id + " non è stata trovata!");
    }
}
