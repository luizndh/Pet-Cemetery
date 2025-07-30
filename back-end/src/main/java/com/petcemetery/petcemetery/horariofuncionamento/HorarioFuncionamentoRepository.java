package com.petcemetery.petcemetery.horariofuncionamento;

import org.springframework.data.mongodb.repository.MongoRepository;

public interface HorarioFuncionamentoRepository extends MongoRepository<HorarioFuncionamento, Long>{
    HorarioFuncionamento findByDiaSemana(String diaSemana);
}
