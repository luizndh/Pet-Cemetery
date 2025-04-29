package com.petcemetery.petcemetery.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = SenhasIguaisValidator.class)
public @interface SenhasIguais {
    String message() default "As senhas devem ser iguais";
    Class<?>[] groups() default {};
    Class<? extends Payload>[] payload() default {};
}
