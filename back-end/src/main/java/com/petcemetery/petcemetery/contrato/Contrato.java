package com.petcemetery.petcemetery.contrato;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.petcemetery.petcemetery.jazigo.Jazigo;
import com.petcemetery.petcemetery.pet.Pet;
import com.petcemetery.petcemetery.servico.Servico;
import com.petcemetery.petcemetery.usuario.cliente.Cliente;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Entity
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
    private LocalDateTime primeiroPagamento;

    @Column(name = "ultimo_pagamento")
    private LocalDateTime ultimoPagamento;

    @OneToOne
    @JoinColumn(name = "tipo_servico")
    private Servico servico;
}
