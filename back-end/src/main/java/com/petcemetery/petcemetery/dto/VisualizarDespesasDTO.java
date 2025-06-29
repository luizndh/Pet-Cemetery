package com.petcemetery.petcemetery.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import com.petcemetery.petcemetery.model.Contrato;

import lombok.Data;

@Data
public class VisualizarDespesasDTO {
    private String tipoServico;
    private BigDecimal valor;
    private LocalDateTime ultimoPagamento;
    private LocalDateTime dataVencimento;

    // Retorna para o front uma lista de despesas, com o tipo de servico, valor. Caso seja um aluguel ou manutenção, também retornará data de vencimento e data do ultimo pagamento.
    public VisualizarDespesasDTO(Contrato contratos){
        this.tipoServico = contratos.getServico().getTipoServico().toString();
        this.valor = contratos.getValor();
        // Caso o servico for um aluguel, a data de vencimento sera o mes seguinte ao ultimo pagamento
        switch (contratos.getServico().getTipoServico().toString()) {
            case "ALUGUEL" -> {
                this.dataVencimento = contratos.getUltimoPagamento().plusMonths(1);
                this.ultimoPagamento = contratos.getUltimoPagamento();
            }
            case "MANUTENCAO" -> {
                this.dataVencimento = contratos.getUltimoPagamento().plusYears(1);
                this.ultimoPagamento = contratos.getUltimoPagamento();
            }
            default -> {
                this.dataVencimento = null;
                this.ultimoPagamento = contratos.getUltimoPagamento();
            }
        }
    }
}
