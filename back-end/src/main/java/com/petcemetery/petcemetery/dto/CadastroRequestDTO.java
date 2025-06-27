package com.petcemetery.petcemetery.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class CadastroRequestDTO {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    @Size(min = 5, message = "A senha deve ter no mínimo 5 caracteres")
    private String senha;

    @NotBlank
    private String senhaRepetida;

    @NotBlank
    private String cpf;

    @NotBlank
    private String cep;

    @NotBlank
    private String rua;

    @NotBlank
    private String numero;

    private String complemento;

    @NotBlank
    private String nome;

    @NotBlank
    @Size(min = 11, max = 11, message = "O telefone deve ter 11 dígitos")
    private String telefone;
}
