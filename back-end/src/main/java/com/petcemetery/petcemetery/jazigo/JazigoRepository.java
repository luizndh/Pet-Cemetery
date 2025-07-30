package com.petcemetery.petcemetery.jazigo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface JazigoRepository extends JpaRepository<Jazigo, Long> {
    List<Jazigo> findAllByProprietarioId(Long id);
    <T> List<T> findAllByOrderByIdAsc(Class<T> type);
}