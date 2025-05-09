package com.petcemetery.petcemetery.repositorio;

import java.util.List;

import com.petcemetery.petcemetery.dto.JazigoMapaDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.petcemetery.petcemetery.model.Jazigo;

public interface JazigoRepository extends JpaRepository<Jazigo, Long> {
    List<Jazigo> findAllByProprietarioId(Long id);
    List<Jazigo> findAllByOrderByIdAsc();

    @Query(value = "SELECT endereco, status FROM Jazigo ORDER BY id ASC", nativeQuery = true)
    List<JazigoMapaDTO> findMapaJazigo();
}