package com.petcemetery.petcemetery.services;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.petcemetery.petcemetery.model.Servico;
import com.petcemetery.petcemetery.model.Servico.ServicoEnum;
import com.petcemetery.petcemetery.repositorio.ServicoRepository;

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
        Servico basic = repository.findByTipoServico(ServicoEnum.BASIC);
        Servico silver = repository.findByTipoServico(ServicoEnum.SILVER);
        Servico gold = repository.findByTipoServico(ServicoEnum.GOLD);

        return Arrays.asList(basic, silver, gold);
    }


}
