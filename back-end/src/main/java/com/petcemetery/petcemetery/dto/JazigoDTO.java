package com.petcemetery.petcemetery.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JazigoDTO {
    private String nomePet;
    private Long idCliente;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataEnterro;
    private String endereco;
    private Long id;

    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate dataNascimento;
    private String especie;
    private String mensagem;
    private String plano;
}