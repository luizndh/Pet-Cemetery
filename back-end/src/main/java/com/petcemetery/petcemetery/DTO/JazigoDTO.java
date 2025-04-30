package com.petcemetery.petcemetery.dto;

import java.time.LocalDate;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JazigoDTO {
    private String nomePet;
    private Long idCliente;
    private LocalDate dataEnterro;
    private String endereco;
    private Long id;
    private LocalDate dataNascimento;
    private String especie;
    private String mensagem;
    private String plano;
}
