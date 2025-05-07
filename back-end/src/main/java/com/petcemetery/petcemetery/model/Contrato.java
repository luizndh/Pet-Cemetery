package com.petcemetery.petcemetery.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.*;

@Getter
@Setter
@Entity(name = "Contrato")
@Table(name = "Contrato")
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Contrato {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_jazigo")
    private Jazigo jazigo;

    @Column(name = "valor")
    private BigDecimal valor;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_servico")
    private LocalDateTime dataServico;

    @OneToOne
    @JoinColumn(name = "id")
    private Pet pet;

    @ManyToOne
    @JoinColumn(name = "id_cliente")
    private Cliente cliente;

    @Column(name = "primeiro_pagamento")
    private LocalDate primeiroPagamento;

    @Column(name = "ultimo_pagamento")
    private LocalDate ultimoPagamento;

    @OneToOne
    @JoinColumn(name = "tipo_servico")
    private Servico servico;
}
