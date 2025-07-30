package com.petcemetery.petcemetery.pet.dto;

import java.time.LocalDate;

import lombok.Data;

@Data
public class PetDTO {
    String nome;
    LocalDate dataNascimento;
    String especie;

    public PetDTO(String nome, LocalDate dataNascimento, String especie) {
        this.nome = nome;
        this.dataNascimento = dataNascimento;
        this.especie = especie;
    }
}
