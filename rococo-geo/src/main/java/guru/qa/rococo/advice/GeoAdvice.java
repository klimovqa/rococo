package guru.qa.rococo.advice;

import guru.qa.rococo.ex.CountryNotFoundException;
import guru.qa.rococo.ex.DuplicateCountryNameException;
import guru.qa.rococo.model.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;


@ControllerAdvice
public class GeoAdvice extends ResponseEntityExceptionHandler {
    @ExceptionHandler(CountryNotFoundException.class)
    public ResponseEntity<Response> handleException(CountryNotFoundException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(DuplicateCountryNameException.class)
    public ResponseEntity<Response> handleException(DuplicateCountryNameException e) {
        Response response = new Response(e.getMessage());
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }
}
