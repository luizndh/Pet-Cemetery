package com.petcemetery.petcemetery.contrato.dto;

import com.petcemetery.petcemetery.servico.Servico.ServicoEnum;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ContratoDTO {

    String cpfCliente;
    Long idJazigo; // Pode ser nulo!
    BigDecimal valor;
    ServicoEnum tipoServico;
    String enderecoJazigo; // Pode ser nulo!
    Long idPet; // Pode ser nulo!
    String dataServico;

    // CONSTRUTOR - Full args
    public ContratoDTO(BigDecimal valor, ServicoEnum tipoServico, String enderecoJazigo, Long idJazigo, Long idPet, String dataServico, String cpfCliente) {
        this.idJazigo = idJazigo;
        this.valor = valor;
        this.tipoServico = tipoServico;
        this.enderecoJazigo = enderecoJazigo;
        this.idPet = idPet;
        this.dataServico = dataServico;
        this.cpfCliente = cpfCliente;
    }

    // CONSTRUTOR - Pet nulo
    public ContratoDTO(BigDecimal valor, ServicoEnum tipoServico, String enderecoJazigo, Long idJazigo, String cpfCliente) {
        this.idJazigo = idJazigo;
        this.valor = valor;
        this.tipoServico = tipoServico;
        this.enderecoJazigo = enderecoJazigo;
        this.cpfCliente = cpfCliente;
    }

    // CONSTRUTOR - Pet e Jazigo nulos
    public ContratoDTO(BigDecimal valor, ServicoEnum tipoServico, String cpfCliente) {
        this.valor = valor;
        this.tipoServico = tipoServico;
        this.cpfCliente = cpfCliente;
    }
}
