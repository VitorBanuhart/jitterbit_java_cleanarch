package com.teste.jitterbit.infra.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;

@RestControllerAdvice
public class RestExceptionHandler {
    @ExceptionHandler(DateTimeParseException.class)
    public ResponseEntity<ApiError> handleInvalidDate(DateTimeParseException ex) {
        ApiError error = new ApiError(
                "Erro na formatação da data",
                HttpStatus.BAD_REQUEST.value(),
                "O formato esperado é ISO 8601 (ex: 2026-03-25T20:05:00Z)",
                LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }
}
