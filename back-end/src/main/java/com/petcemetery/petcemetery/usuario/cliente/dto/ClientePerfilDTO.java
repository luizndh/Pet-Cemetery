package com.petcemetery.petcemetery.usuario.cliente.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ClientePerfilDTO {

    String nome;
    String email;
    String telefone;
}
