package com.petcemetery.petcemetery.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ReuniaoDTO {
    private String cpfCliente;
    private String data;
    private String assunto;

    public ReuniaoDTO(String cpfCliente, String data, String assunto) {
        this.cpfCliente = cpfCliente;
        this.data = data;
        this.assunto = assunto;
    }
}
