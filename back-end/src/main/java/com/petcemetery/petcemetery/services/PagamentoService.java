package com.petcemetery.petcemetery.services;

import org.springframework.stereotype.Service;

import com.petcemetery.petcemetery.model.Contrato;
import com.petcemetery.petcemetery.model.Pagamento;
import com.petcemetery.petcemetery.repositorio.PagamentoRepository;

@Service
public class PagamentoService {

    private final PagamentoRepository repository;

    public PagamentoService(PagamentoRepository repository) {
        this.repository = repository;
    }

    public void save(Pagamento pagamento) {
        this.repository.save(pagamento);
    }

    public Pagamento findByContrato(Contrato contrato) {
        return this.repository.findByContrato(contrato);
    }

}
