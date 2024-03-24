package spring.course.application.exception;

import org.springframework.http.HttpStatus;

import java.sql.Timestamp;

public record ApiException(String message, HttpStatus httpStatus, Timestamp timestamp) {
}
