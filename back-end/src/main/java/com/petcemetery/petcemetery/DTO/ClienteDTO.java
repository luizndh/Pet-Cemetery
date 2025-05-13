package com.petcemetery.petcemetery.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClienteDTO {
    private String email;
    private String telefone;
    private String nome;
    private String rua;
    private String numero;
    private String complemento;
    private String cep;
    private String cpf;
}
