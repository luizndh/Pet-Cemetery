package com.petcemetery.petcemetery.validation;

import com.petcemetery.petcemetery.dto.CadastroRequestDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SenhasIguaisValidator implements ConstraintValidator<SenhasIguais, CadastroRequestDTO> {

    @Override
    public boolean isValid(CadastroRequestDTO dto, ConstraintValidatorContext context) {
        if (dto.getSenha() == null || dto.getSenhaRepetida() == null) {
            return false;
        }
        return dto.getSenha().equals(dto.getSenhaRepetida());
    }
}
