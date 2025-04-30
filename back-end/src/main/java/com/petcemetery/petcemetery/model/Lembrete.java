package com.petcemetery.petcemetery.model;

import java.time.LocalDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Getter
@Setter
@Table(name = "Lembrete")
@Entity(name = "Lembrete")
@NoArgsConstructor
public class Lembrete {
    @Id
    @Column(name = "id")
    @GeneratedValue
    private Long id;

    @Column(name = "data")
    private LocalDate data;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente cliente;

    @Column(name = "enviado")
    private Boolean enviado;

    public Lembrete (LocalDate data, Cliente cliente) {
        this.data = data;
        this.cliente = cliente;
    }

}
