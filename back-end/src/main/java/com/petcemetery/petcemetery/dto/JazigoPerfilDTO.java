package com.petcemetery.petcemetery.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JazigoPerfilDTO {
    private String mensagem;
    private String foto;
    private String plano;
}
