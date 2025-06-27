package com.petcemetery.petcemetery.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class OcupacaoJazigoDTO {
    private String jazigo;
    private String cpf;
    private String plano;
    private String pet;
    private String especie;
    private String dataEnterro;
}
