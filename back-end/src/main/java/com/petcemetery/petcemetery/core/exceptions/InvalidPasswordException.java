package com.petcemetery.petcemetery.core.exceptions;

/**
 * Exceção lançada quando credenciais de senha são inválidas.
 * Usada em cenários de autenticação e troca de senha.
 */
public class InvalidPasswordException extends RuntimeException {

    public InvalidPasswordException(String message) {
        super(message);
    }

    public InvalidPasswordException() {
        super("Senha incorreta");
    }
}
