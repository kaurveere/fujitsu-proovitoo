package spring.course.application.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.Timestamp;
@ControllerAdvice
public class ApiExceptionHandler {
    @ExceptionHandler(value = ForbiddenVehicleException.class)
    public ResponseEntity<Object> handleForbiddenVehicleException(ForbiddenVehicleException e) {
        ApiException apiException = new ApiException(
                e.getMessage(),
                HttpStatus.BAD_REQUEST,
                new Timestamp(System.currentTimeMillis()));
        return new ResponseEntity<>(apiException, HttpStatus.BAD_REQUEST);
    }
}
