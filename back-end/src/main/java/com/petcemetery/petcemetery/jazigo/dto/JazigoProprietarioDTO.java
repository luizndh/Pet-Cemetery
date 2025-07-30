package com.petcemetery.petcemetery.jazigo.dto;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class JazigoProprietarioDTO {
    private Long id;
    private String nomePet;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm")
    private LocalDateTime dataEnterro;
}