package com.petcemetery.petcemetery.pagamento;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petcemetery.petcemetery.contrato.Contrato;

public interface PagamentoRepository extends JpaRepository<Pagamento, Long> {
    Pagamento findByContrato(Contrato contrato);
}
