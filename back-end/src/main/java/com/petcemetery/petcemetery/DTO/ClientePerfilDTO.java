package com.petcemetery.petcemetery.dto;

import lombok.Data;

@Data
public class ClientePerfilDTO {

    String nome;
    String email;

    public ClientePerfilDTO(String nome, String email) {
        this.nome = nome;
        this.email = email;
    }
}
