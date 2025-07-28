package cosimocrupi.L1.exceptions;

import lombok.Getter;

import java.util.List;
@Getter
public class ValidationException extends RuntimeException {
    private List<String> errorMess;
    public ValidationException(List<String> errorMess) {
        super("Errori di validazione!");
        this.errorMess=errorMess;
    }
}
