package com.petcemetery.petcemetery.model;

import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Getter
@Setter
@Entity(name = "Servicos")
@Table(name = "Servicos")
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Servico {

    @Id
    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_servico")
    private ServicoEnum tipoServico;

    @Column(name = "valor")
    private BigDecimal valor;

    public enum ServicoEnum {
        COMPRA,
        ALUGUEL,
        PERSONALIZACAO,
        MANUTENCAO,
        EXUMACAO,
        ENTERRO,
        BASIC,
        SILVER,
        GOLD;
    }
}
