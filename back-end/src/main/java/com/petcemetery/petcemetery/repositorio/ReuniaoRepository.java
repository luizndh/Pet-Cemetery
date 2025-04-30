package com.petcemetery.petcemetery.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.petcemetery.petcemetery.model.Reuniao;

public interface ReuniaoRepository extends JpaRepository<Reuniao, Long>{
    List<Reuniao> findAllByOrderByDataAsc();
}
