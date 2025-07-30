package com.petcemetery.petcemetery.contrato;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petcemetery.petcemetery.servico.Servico;
import com.petcemetery.petcemetery.servico.Servico.ServicoEnum;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {
    List<Contrato> findAllByClienteId(Long id);
    List<Contrato> findByServico(Servico servico);
    List<Contrato> findByServicoTipoServico(ServicoEnum servicoEnum);
}