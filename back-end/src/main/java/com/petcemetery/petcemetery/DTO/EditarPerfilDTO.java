package com.petcemetery.petcemetery.dto;

import jakarta.validation.constraints.Email;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class EditarPerfilDTO {
    private String cep;
    private String rua;
    private String numero;
    private String complemento;
    private String nome;
    private String telefone;
}
