package com.petcemetery.petcemetery.usuario.cliente.dto;

import com.petcemetery.petcemetery.usuario.cliente.validation.SenhasIguais;
import lombok.Data;

@Data
@SenhasIguais(message = "A nova senha e a confirmação devem ser iguais")
public class TrocarSenhaDTO {
    private String senhaAtual;
    private String novaSenha;
    private String confirmarSenha;
}
