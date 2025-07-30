package com.petcemetery.petcemetery.reuniao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ReuniaoRepository extends JpaRepository<Reuniao, Long>{
    List<Reuniao> findAllByOrderByDataAsc();
}
