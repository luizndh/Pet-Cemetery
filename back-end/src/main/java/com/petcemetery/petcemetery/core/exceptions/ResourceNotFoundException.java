package com.petcemetery.petcemetery.core.exceptions;

/**
 * Exceção lançada quando um recurso não é encontrado no banco de dados.
 * Esta exceção é mais específica que IllegalArgumentException e facilita
 * o tratamento adequado de recursos não encontrados.
 */
public class ResourceNotFoundException extends RuntimeException {

    private final String resourceName;
    private final Object resourceId;

    public ResourceNotFoundException(String resourceName, Object resourceId) {
        super(String.format("%s não encontrado(a) com ID: %s", resourceName, resourceId));
        this.resourceName = resourceName;
        this.resourceId = resourceId;
    }

    public ResourceNotFoundException(String resourceName, String fieldName, Object fieldValue) {
        super(String.format("%s não encontrado(a) com %s: %s", resourceName, fieldName, fieldValue));
        this.resourceName = resourceName;
        this.resourceId = fieldValue;
    }

    public String getResourceName() {
        return resourceName;
    }

    public Object getResourceId() {
        return resourceId;
    }
}
