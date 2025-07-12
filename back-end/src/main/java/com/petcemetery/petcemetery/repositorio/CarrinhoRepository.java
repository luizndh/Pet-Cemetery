package com.petcemetery.petcemetery.repositorio;

import com.petcemetery.petcemetery.dto.ItemCarrinhoDTO;
import com.petcemetery.petcemetery.model.ItemCarrinho;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarrinhoRepository extends JpaRepository<ItemCarrinho, Long> {

    List<ItemCarrinhoDTO> findAllByClienteId(Long id);
}
