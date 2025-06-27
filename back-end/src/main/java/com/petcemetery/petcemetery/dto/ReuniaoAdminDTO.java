package com.petcemetery.petcemetery.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class ReuniaoAdminDTO {
    private String data;
    private String hora;
    private String assunto;
    private String email;
    private String cpf;
}
