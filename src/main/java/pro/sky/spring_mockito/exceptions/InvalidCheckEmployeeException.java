package pro.sky.spring_mockito.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class InvalidCheckEmployeeException extends RuntimeException {
    public InvalidCheckEmployeeException(String message) {
        super(message);
    }

}
