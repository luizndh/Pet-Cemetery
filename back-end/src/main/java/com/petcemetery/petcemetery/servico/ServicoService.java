package com.petcemetery.petcemetery.servico;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.petcemetery.petcemetery.servico.Servico.ServicoEnum;

@Service
public class ServicoService {

    private final ServicoRepository repository;

    public ServicoService(ServicoRepository repository) {
        this.repository = repository;
    }

    public List<Servico> exibirServicos() {
        return repository.findAll();
    }

    public boolean alterarServicos(String nomeServico, BigDecimal valor) {
        ServicoEnum servicoEnum = ServicoEnum.valueOf(nomeServico);
        Servico servicoEntity = repository.findByTipoServico(servicoEnum);

        servicoEntity.setValor(valor);
        repository.save(servicoEntity);

        return true;
    }

    public Servico findByTipoServico(ServicoEnum servico) {
        return this.repository.findByTipoServico(servico);
    }

    public List<Servico> listarPlanos() {
        return repository.findPlanos();
    }

    public List<Servico> buscarServicosParaOrnamentacao(String compraOuAluguel) {
    List<ServicoEnum> tipos = Arrays.asList(
            ServicoEnum.valueOf(compraOuAluguel.toUpperCase()),
            ServicoEnum.BASIC,
            ServicoEnum.SILVER,
            ServicoEnum.GOLD);
        return repository.findServicosByTipos(tipos);
    }


}
