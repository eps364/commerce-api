package br.com.commerce.api.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;


@RestControllerAdvice
public class HandleMethodArgumentNotValidException extends GlobalExceptionHandler {

        @ExceptionHandler(MethodArgumentNotValidException.class)
        @ResponseStatus(HttpStatus.UNPROCESSABLE_ENTITY)
        public ResponseEntity<ExceptionResponse> handleMethodArgumentNotValid(
                        MethodArgumentNotValidException ex,
                        WebRequest request) {
                ExceptionResponse errorResponse = new ExceptionResponse(
                                HttpStatus.UNPROCESSABLE_ENTITY.value(),
                                "Validation error. Check 'errors' field for details.");

                for (FieldError fieldError : ex.getBindingResult().getFieldErrors()) {
                        errorResponse.addValidationError(fieldError.getField(),
                                        fieldError.getDefaultMessage());
                }

                return ResponseEntity.unprocessableEntity().body(errorResponse);
        }

}
