package guru.qa.rococo.advice;

import guru.qa.rococo.ex.MuseumNotFoundException;
import guru.qa.rococo.model.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class MuseumAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(MuseumNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleException(MuseumNotFoundException exception) {
        return new ResponseEntity<>(new ErrorResponse(exception.getMessage()), HttpStatus.NOT_FOUND);
    }
}
