package com.petcemetery.petcemetery.core.exceptions;

import java.util.NoSuchElementException;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import lombok.extern.slf4j.Slf4j;

/**
 * Handler global para exceções da aplicação.
 * Centraliza o tratamento de erros e fornece respostas consistentes.
 */
@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    /**
     * Trata exceções de recurso não encontrado.
     */
    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ApiErrorMessage> handleResourceNotFound(ResourceNotFoundException e) {
        log.warn("Recurso não encontrado: {}", e.getMessage());
        ApiErrorMessage error = new ApiErrorMessage(HttpStatus.NOT_FOUND.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * Trata exceções de senha inválida.
     */
    @ExceptionHandler(InvalidPasswordException.class)
    public ResponseEntity<ApiErrorMessage> handleInvalidPassword(InvalidPasswordException e) {
        log.warn("Tentativa de senha inválida: {}", e.getMessage());
        ApiErrorMessage error = new ApiErrorMessage(HttpStatus.UNAUTHORIZED.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }

    /**
     * Trata exceções de regras de negócio.
     */
    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<ApiErrorMessage> handleBusinessException(BusinessException e) {
        log.warn("Violação de regra de negócio: {}", e.getMessage());
        ApiErrorMessage error = new ApiErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

    /**
     * Trata exceções de validação de argumentos.
     */
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ApiErrorMessage> handleIllegalArgument(IllegalArgumentException e) {
        log.warn("Argumento inválido: {}", e.getMessage());
        ApiErrorMessage error = new ApiErrorMessage(HttpStatus.UNPROCESSABLE_ENTITY.value(), e.getMessage());
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(error);
    }

    /**
     * Trata exceções de validação de bean (Jakarta Validation).
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ApiErrorMessage> handleValidationErrors(MethodArgumentNotValidException e) {
        String errorMessage = e.getBindingResult().getFieldErrors().stream()
                .map(error -> error.getField() + ": " + error.getDefaultMessage())
                .findFirst()
                .orElse("Erro de validação");

        log.warn("Erro de validação: {}", errorMessage);
        ApiErrorMessage error = new ApiErrorMessage(HttpStatus.BAD_REQUEST.value(), errorMessage);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(error);
    }

    /**
     * Trata exceções de elemento não encontrado.
     */
    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<ApiErrorMessage> handleNoSuchElement(NoSuchElementException e) {
        log.warn("Elemento não encontrado: {}", e.getMessage());
        ApiErrorMessage error = new ApiErrorMessage(HttpStatus.NOT_FOUND.value(),
                e.getMessage() != null ? e.getMessage() : "Recurso não encontrado");
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    /**
     * Trata exceções genéricas não capturadas.
     */
    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ApiErrorMessage> handleRuntimeException(RuntimeException e) {
        log.error("Erro interno do servidor", e);
        ApiErrorMessage error = new ApiErrorMessage(HttpStatus.INTERNAL_SERVER_ERROR.value(),
                "Erro interno do servidor. Contate o suporte.");
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(error);
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ApiErrorMessage> handleException(BadCredentialsException e) {
        log.error("Credenciais inválidas", e);
        ApiErrorMessage error = new ApiErrorMessage(HttpStatus.UNAUTHORIZED.value(),
                "Credenciais inválidas.");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(error);
    }
}
