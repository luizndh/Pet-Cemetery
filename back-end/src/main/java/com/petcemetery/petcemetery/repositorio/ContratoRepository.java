package com.petcemetery.petcemetery.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.petcemetery.petcemetery.model.Contrato;
import com.petcemetery.petcemetery.model.Servico;
import com.petcemetery.petcemetery.model.Servico.ServicoEnum;

public interface ContratoRepository extends JpaRepository<Contrato, Long> {
    List<Contrato> findAllByClienteId(Long id);
    List<Contrato> findByServico(Servico servico);
    List<Contrato> findByServicoTipoServico(ServicoEnum servicoEnum);
}