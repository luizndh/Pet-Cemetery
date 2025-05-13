package com.petcemetery.petcemetery.dto;

import com.petcemetery.petcemetery.validation.SenhasIguais;
import lombok.Data;

@Data
@SenhasIguais(message = "A nova senha e a confirmação devem ser iguais")
public class TrocarSenhaDTO {
    private String senhaAtual;
    private String novaSenha;
    private String confirmarSenha;
}
