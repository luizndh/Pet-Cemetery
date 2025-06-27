package com.petcemetery.petcemetery.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class AquisicaoJazigoDTO {

    String endereco;
    BigDecimal valor;

    public AquisicaoJazigoDTO(String endereco, BigDecimal valor) {
        this.endereco = endereco;
        this.valor = valor;
    }
}
