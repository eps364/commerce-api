package br.com.commerce.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;

public class HandleHttpRequestMethodNotSupportedException extends GlobalExceptionHandler {

    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ExceptionResponse> handleHttpRequestMethodNotSupportedException(
            HttpRequestMethodNotSupportedException ex,
            WebRequest request) {
        ExceptionResponse errorResponse = new ExceptionResponse(
                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                "MethodNotSupportedException type in properties:  - "
                        + ex.getCause().getMessage());

        
        return ResponseEntity.unprocessableEntity().body(errorResponse);
    }

}
