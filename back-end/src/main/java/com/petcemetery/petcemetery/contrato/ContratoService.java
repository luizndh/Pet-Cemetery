package com.petcemetery.petcemetery.contrato;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.petcemetery.petcemetery.contrato.dto.ContratoDTO;
import com.petcemetery.petcemetery.usuario.cliente.dto.VisualizarDespesasDTO;
import com.petcemetery.petcemetery.servico.Servico.ServicoEnum;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ContratoService {

    private final ContratoRepository repository;

    public List<ContratoDTO> findEnterros() {
        List<Contrato> contratos = repository.findByServicoTipoServico(ServicoEnum.ENTERRO);
        List<ContratoDTO> enterros = new ArrayList<>();

        for (Contrato contrato : contratos) {
            ContratoDTO contratoDTO = new ContratoDTO(
                    contrato.getValor(),
                    contrato.getServico().getTipoServico(),
                    contrato.getJazigo().getEndereco(),
                    contrato.getJazigo().getId(),
                    contrato.getPet().getId(),
                    contrato.getPet().getDataEnterro().toString(),
                    contrato.getCliente().getCpf());

            enterros.add(contratoDTO);
        }

        return enterros;
    }

    public List<ContratoDTO> findExumacoes() {
        List<Contrato> contratos = repository.findByServicoTipoServico(ServicoEnum.EXUMACAO);
        List<ContratoDTO> exumacoes = new ArrayList<>();

        for (Contrato contrato : contratos) {
            ContratoDTO contratoDTO = new ContratoDTO(
                    contrato.getValor(),
                    contrato.getServico().getTipoServico(),
                    contrato.getJazigo().getEndereco(),
                    contrato.getJazigo().getId(),
                    contrato.getPet().getId(),
                    contrato.getPet().getDataEnterro().toString(),
                    contrato.getCliente().getCpf());

            exumacoes.add(contratoDTO);
        }

        return exumacoes;
    }

    public List<VisualizarDespesasDTO> visualizarDespesas(Long id) {
        List<Contrato> contratos = repository.findAllByClienteId(id);

        List<VisualizarDespesasDTO> despesasDTO = new ArrayList<>();

        for (Contrato c : contratos) {
            VisualizarDespesasDTO despesaDTO = new VisualizarDespesasDTO(c);
            despesasDTO.add(despesaDTO);
        }

        return despesasDTO;
    }

    public void save(Contrato contrato) {
        this.repository.save(contrato);
    }

}
