package com.petcemetery.petcemetery.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@MappedSuperclass
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Usuario implements UserDetails {

    @Id
    @GeneratedValue
    private Long id;

    @Column(name = "cpf")
    @NotBlank
    @Size(min = 11, max = 11, message = "O CPF deve ter 11 dígitos")
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

    @Enumerated(EnumType.STRING)
    private Role role;

    public Usuario(String email, String telefone, String nome, String cpf, String cep, String rua, String numero, String complemento, String senha, Role role) {
        System.out.println("construtor do usuario");
        this.email = email;
        this.telefone = telefone;
        this.nome = nome;
        this.cpf = cpf;
        this.cep = cep;
        this.rua = rua;
        this.numero = numero;
        this.complemento = complemento;
        this.senha = senha;
        this.role = role;
    }

    public Usuario(String email, String telefone, String nome, String cpf, String senha) {
        this.email = email;
        this.telefone = telefone;
        this.nome = nome;
        this.cpf = cpf;
        this.senha = senha;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of(new SimpleGrantedAuthority(role.name()));
    }

    @Override
    public String getPassword() {
        return senha;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}