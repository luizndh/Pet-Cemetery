package com.petcemetery.petcemetery.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

@Data
@Builder
@ToString
public class ReuniaoDTO {
    private String data;
    private String hora;
    private String assunto;
}
