package com.petcemetery.petcemetery.dto;

import java.util.Date;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JazigoProprietarioDTO {
    private String nomePet;
    private Date dataEnterro;
}