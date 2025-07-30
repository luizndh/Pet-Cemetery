package com.petcemetery.petcemetery.lembrete;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface LembreteRepository extends JpaRepository<Lembrete, Long>{
    Lembrete findByData(LocalDate data);
    List<Lembrete> findAllByEnviado(boolean enviado);
}
