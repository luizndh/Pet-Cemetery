package com.petcemetery.petcemetery.model;

import java.time.LocalDateTime;
import java.time.LocalTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Entity(name = "Reuniao")
@Table(name = "Reuniao")
@Getter
@Setter
public class Reuniao {

    @Id
    @GeneratedValue
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente cliente;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data")
    private LocalDateTime data;

    @Column(name = "assunto")
    private String assunto;

    public Reuniao(Cliente cliente, LocalDateTime data, String assunto) {
        this.cliente = cliente;
        this.data = data;
        this.assunto = assunto;
    }
}
