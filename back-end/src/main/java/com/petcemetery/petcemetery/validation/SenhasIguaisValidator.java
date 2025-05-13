package com.petcemetery.petcemetery.validation;

import com.petcemetery.petcemetery.dto.CadastroRequestDTO;
import com.petcemetery.petcemetery.dto.TrocarSenhaDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SenhasIguaisValidator implements ConstraintValidator<SenhasIguais, Object> {

    @Override
    public boolean isValid(Object obj, ConstraintValidatorContext context) {
        if (obj instanceof CadastroRequestDTO dto) {
            return dto.getSenha() != null && dto.getSenha().equals(dto.getSenhaRepetida());
        } else if (obj instanceof TrocarSenhaDTO dto) {
            return dto.getNovaSenha() != null && dto.getNovaSenha().equals(dto.getConfirmarSenha());
        }
        return false;
    }
}
