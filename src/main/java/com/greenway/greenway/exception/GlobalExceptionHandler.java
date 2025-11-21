package com.greenway.greenway.exception;

import jakarta.validation.ConstraintViolationException;

import org.springframework.context.MessageSource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Locale;
import java.util.Map;
import java.util.NoSuchElementException;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private final MessageSource messageSource;

    public GlobalExceptionHandler(MessageSource messageSource) {
        this.messageSource = messageSource;
    }

    // Handle @Valid errors (DTO)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, String>> handleValidationExceptions(
            MethodArgumentNotValidException ex, Locale locale) {

        Map<String, String> errors = new HashMap<>();

        for (FieldError error : ex.getBindingResult().getFieldErrors()) {
            errors.put(error.getField(), error.getDefaultMessage());
        }

        return ResponseEntity.badRequest().body(errors);
    }

    // Handle @Valid errors em parâmetros e path variables
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<Map<String, String>> handleConstraintViolation(
            ConstraintViolationException ex) {

        Map<String, String> errors = new HashMap<>();

        ex.getConstraintViolations().forEach(cv -> {
            errors.put(cv.getPropertyPath().toString(), cv.getMessage());
        });

        return ResponseEntity.badRequest().body(errors);
    }

    // Handle not found (quando service usa orElseThrow)
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<String> handleNotFound(NoSuchElementException ex, Locale locale) {

        String msg = messageSource.getMessage(
                "resource.notfound",
                null,
                "Recurso não encontrado",
                locale
        );

        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(msg);
    }

    // Fallback geral + tradução de mensagens como user.email.duplicate
    @ExceptionHandler(Exception.class)
    public ResponseEntity<String> handleAll(Exception ex, Locale locale) {

        String messageKey = ex.getMessage();
        String msg;

        // Se for chave do messages.properties
        if (messageKey != null && messageKey.startsWith("user.")) {
            msg = messageSource.getMessage(messageKey, null, messageKey, locale);
        } else {
            msg = messageSource.getMessage(
                    "internal.error",
                    null,
                    "Erro interno no servidor",
                    locale
            );
        }

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(msg);
    }
}
