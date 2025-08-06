package com.petcemetery.petcemetery.jazigo.dto;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.petcemetery.petcemetery.jazigo.Jazigo;
import com.petcemetery.petcemetery.pet.Pet;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class DetalharJazigoDTO{
    private String nomePet;
    private String status;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;
    private String especiePet;
    private String nomeProrietario;
    private String enderecoJazigo;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataEnterro;
    private String plano;
    private String mensagemLapide;
    private String urlImagem;


    public DetalharJazigoDTO(Jazigo jazigo, String urlImagem){
        if (jazigo.getPetEnterrado() != null) {
            Pet pet = jazigo.getPetEnterrado();
            this.nomePet = pet.getNome();
            this.dataNascimento = pet.getDataNascimento();
            this.dataEnterro = pet.getDataEnterro();
            this.especiePet = pet.getEspecie();
        }

        if (jazigo.getPlano() != null){
            this.plano = jazigo.getPlano().toString();
        }

        this.nomeProrietario = jazigo.getProprietario().getNome();
        this.enderecoJazigo = jazigo.getEndereco();
        this.mensagemLapide = jazigo.getMensagem();
        this.urlImagem = urlImagem;
        this.status = jazigo.getStatus() == Jazigo.StatusEnum.OCUPADO ? "Ocupado" : "Disponível";
    }

}