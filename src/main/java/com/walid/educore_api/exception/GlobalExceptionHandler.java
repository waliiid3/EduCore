package com.walid.educore_api.exception;

import com.walid.educore_api.dto.response.ValidationErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationErrorResponse> handleValidationException(
            MethodArgumentNotValidException exception) {

        Map<String, List<String>> errors = new HashMap<>();

        exception.getBindingResult()
                .getFieldErrors()
                .forEach(error ->
                        errors
                                .computeIfAbsent(
                                        error.getField(),
                                        key -> new ArrayList<>()
                                )
                                .add(error.getDefaultMessage())
                );

        ValidationErrorResponse response =
                new ValidationErrorResponse(
                        LocalDateTime.now(),
                        HttpStatus.BAD_REQUEST.value(),
                        "Validation failed",
                        errors
                );

        return new ResponseEntity<>(
                response,
                HttpStatus.BAD_REQUEST
        );
    }
}