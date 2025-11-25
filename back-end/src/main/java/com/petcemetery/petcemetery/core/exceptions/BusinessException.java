package com.petcemetery.petcemetery.core.exceptions;

/**
 * Exceção base para violações de regras de negócio.
 * Deve ser estendida para criar exceções específicas de domínio.
 */
public class BusinessException extends RuntimeException {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }
}
