package com.petcemetery.petcemetery.model;

import java.time.LocalDate;
import java.time.LocalDateTime;

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
import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Pet")
@Table(name = "Pet")
public class Pet {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_cliente", referencedColumnName = "id")
    private Cliente proprietario;

    @Column(name = "nome")
    private String nome;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_enterro")
    private LocalDateTime dataEnterro;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "data_exumacao")
    private LocalDateTime dataExumacao;

    @Column(name = "data_nascimento")
    private LocalDate dataNascimento;

    @Column(name = "especie")
    private String especie;

    public Pet(String nome, String especie, Cliente proprietario) {
        this.nome = nome;
        this.especie = especie;
        this.proprietario = proprietario;
    }
    public Pet(String nome, LocalDate dataNascimento, String especie, Cliente proprietario) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.especie = especie;
        this.proprietario = proprietario;
    }
    public Pet(LocalDateTime data, Cliente proprietario) {
        this.dataEnterro = data;
        this.proprietario = proprietario;
    }
    public Pet(String nome, LocalDateTime dataEnterro, LocalDate dataNascimento, String especie, Cliente proprietario) {
        this.nome = nome;
        this.dataEnterro = dataEnterro;
        this.dataNascimento = dataNascimento;
        this.especie = especie;
        this.proprietario = proprietario;
    }
    public Pet(String nome, LocalDateTime dataEnterro, LocalDate dataNascimento, String especie, Cliente proprietario, LocalDateTime dataExumacao) {
        this.nome = nome;
        this.dataEnterro = dataEnterro;
        this.dataNascimento = dataNascimento;
        this.especie = especie;
        this.proprietario = proprietario;
        this.dataExumacao = dataExumacao;
    }

}
