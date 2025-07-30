package com.petcemetery.petcemetery.pagamento;

import org.springframework.stereotype.Service;

import com.petcemetery.petcemetery.contrato.Contrato;

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
