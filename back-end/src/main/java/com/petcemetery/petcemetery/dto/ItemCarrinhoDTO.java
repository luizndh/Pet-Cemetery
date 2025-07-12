package com.petcemetery.petcemetery.dto;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Builder
public class ItemCarrinhoDTO {
    private Long idJazigo;
    private String enderecoJazigo;
    private String tipoServico;
    private BigDecimal valorServico;
    private String planoSelecionado;
    private BigDecimal valorPlano;
}
