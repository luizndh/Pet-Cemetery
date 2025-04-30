package com.petcemetery.petcemetery.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CarrinhoDTO {
    Long id;
    String jazigoId;
    String selectedOrnament;
    String tipo;
    BigDecimal valor;
}
