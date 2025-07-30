package com.petcemetery.petcemetery.integracao.weather.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class ClimaDTO {
    private String temperaturaMaxima;
    private String temperaturaMinima;
    private String icone;
}
