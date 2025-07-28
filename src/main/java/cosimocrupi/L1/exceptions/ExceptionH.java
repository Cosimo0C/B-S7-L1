package cosimocrupi.L1.exceptions;

import cosimocrupi.L1.payloads.ErrorsDTO;
import cosimocrupi.L1.payloads.ErrorsListDTO;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
public class ExceptionH {


    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ErrorsDTO serverError(Exception e){
        e.printStackTrace();
        return new ErrorsDTO("Errore! Riprova più tardi!", LocalDateTime.now());
    }

    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ErrorsDTO badRequest(BadRequestException e){
        return new ErrorsDTO(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ErrorsDTO notFound(NotFoundException e){
        return new ErrorsDTO(e.getMessage(), LocalDateTime.now());
    }

    @ExceptionHandler(ValidationException.class)
    @ResponseStatus(HttpStatus.BAD_GATEWAY)
    public ErrorsListDTO validationError(ValidationException e){
        return new ErrorsListDTO(e.getMessage(), LocalDateTime.now(), e.getErrorMess());
    }
}
