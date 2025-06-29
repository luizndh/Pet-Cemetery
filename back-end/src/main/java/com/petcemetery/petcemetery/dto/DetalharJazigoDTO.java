package com.petcemetery.petcemetery.dto;


import com.petcemetery.petcemetery.model.Jazigo;
import com.petcemetery.petcemetery.model.Pet;

import lombok.Data;

@Data
public class DetalharJazigoDTO{
    private String nomePet;
    private String dataNascimento;
    private String especiePet;
    private String nomeProrietario;
    private String enderecoJazigo;
    private String dataEnterro;
    private String plano;
    private String mensagemLapide;
    private String urlImagem;


    public DetalharJazigoDTO(Pet pet, Jazigo jazigo){
        if ( pet == null){
            this.nomePet = null;
            this.dataEnterro = null;
            this.dataNascimento = null;
            this.especiePet = null;
        }
        else{
            this.nomePet = pet.getNome();
            this.dataNascimento = pet.getDataNascimento().toString();
            this.dataNascimento = pet.getDataNascimento().toString();
            this.especiePet = pet.getEspecie();
        }

        if (jazigo.getPlano() == null){
            this.plano = null;
        }
        else{
            this.plano = jazigo.getPlano().toString();
        }

        this.nomeProrietario = jazigo.getProprietario().getNome();
        this.enderecoJazigo = jazigo.getEndereco();
        this.mensagemLapide = jazigo.getMensagem();
        this.urlImagem = jazigo.getFoto();
    }

}