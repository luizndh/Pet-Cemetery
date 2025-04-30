package com.petcemetery.petcemetery.model;

import java.math.BigDecimal;
import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity(name = "Pagamento")
@Table(name = "Pagamento")
@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Pagamento {

    @Id
    @GeneratedValue
    @Column(name = "i")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente cliente;

    @Column(name = "valor")
    private BigDecimal valor;

    @Column(name = "data_pagamento")
    private LocalDate dataPagamento;

    @Column(name = "data_vencimento")
    private LocalDate dataVencimento;

    @Column(name = "isPago")
    private Boolean pago;

    @OneToOne
    @JoinColumn(name = "id_contrato")
    private Contrato contrato;


    @Column(name = "metodo_pagamento")
    @Enumerated(EnumType.STRING)
    private MetodoEnum metodoPagamento;

    public boolean isPago() {
        return this.pago;
    }

    public enum MetodoEnum{
        CREDITO,
        DEBITO,
        PAYPAL
    }

    public Pagamento(Cliente cliente, BigDecimal valor, LocalDate dataPagamento, LocalDate dataVencimento, Boolean pago, Contrato contrato, MetodoEnum metodoPagamento) {
        this.cliente = cliente;
        this.valor = valor;
        this.dataPagamento = dataPagamento;
        this.dataVencimento = dataVencimento;
        this.pago = pago;
        this.contrato = contrato;
        this.metodoPagamento = metodoPagamento;
    }
}
