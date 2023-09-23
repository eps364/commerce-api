package br.com.commerce.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;


@RestControllerAdvice
public class HandlerMethodArgumentTypeMismatchException {

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionResponse> handleMethodArgumentTypeMismatchException(
            MethodArgumentTypeMismatchException ex,
            WebRequest request) {
        ExceptionResponse errorResponse = new ExceptionResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "Incompatible type in properties: " + ex.getPropertyName() + " - "
                        + ex.getCause().getMessage());

        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }

}
