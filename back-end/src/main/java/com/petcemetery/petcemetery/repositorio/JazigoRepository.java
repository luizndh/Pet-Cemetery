package com.petcemetery.petcemetery.repositorio;

import java.util.List;

import com.petcemetery.petcemetery.dto.JazigoMapaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.petcemetery.petcemetery.model.Jazigo;

public interface JazigoRepository extends JpaRepository<Jazigo, Long> {
    List<Jazigo> findAllByProprietarioId(Long id);
    <T> List<T> findAllByOrderByIdAsc(Class<T> type);
}