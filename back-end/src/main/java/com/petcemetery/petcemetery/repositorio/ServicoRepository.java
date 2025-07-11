package com.petcemetery.petcemetery.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.petcemetery.petcemetery.model.Servico;
import com.petcemetery.petcemetery.model.Servico.ServicoEnum;

public interface ServicoRepository extends JpaRepository<Servico, ServicoEnum> {
    Servico findByTipoServico(ServicoEnum servicoEnum);

    @Query("SELECT s FROM Servico s WHERE s.tipoServico IN ('BASIC', 'SILVER', 'GOLD')")
    List<Servico> findPlanos();

    @Query("SELECT s FROM Servico s WHERE s.tipoServico IN :tipos")
    List<Servico> findServicosByTipos(List<ServicoEnum> tipos);
}