package com.petcemetery.petcemetery.model;
import jakarta.persistence.Column;
import jakarta.persistence.Id;
import jakarta.persistence.MappedSuperclass;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@MappedSuperclass
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Usuario {

    @Id
    @Column(name = "cpf")
    private String cpf;

    @Column(name = "email")
    @Email
    @NotBlank
    private String email;

    @Column(name = "telefone")
    @NotBlank
    @Size(min = 11, max = 11, message = "O telefone deve ter 11 dígitos")
    private String telefone;

    @Column(name = "nome")
    @NotBlank
    private String nome;

    @Column(name = "cep")
    @NotBlank
    @Size(min = 8, max = 8, message = "O CEP deve ter 8 dígitos")
    private String cep;

    @Column(name = "rua")
    @NotBlank
    private String rua;

    @Column(name = "numero")
    @NotBlank
    private String numero;

    @Column(name = "complemento")
    private String complemento;

    @Column(name = "senha")
    @NotBlank
    @Size(min = 5, message = "A senha deve ter no mínimo 5 caracteres")
    private String senha;

    public Usuario(String email, String telefone, String nome, String cpf, String senha) {
        this.email = email;
        this.telefone = telefone;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }
}