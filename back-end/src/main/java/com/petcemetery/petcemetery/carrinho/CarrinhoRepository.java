package com.petcemetery.petcemetery.carrinho;

import com.petcemetery.petcemetery.carrinho.dto.ItemCarrinhoDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CarrinhoRepository extends JpaRepository<ItemCarrinho, Long> {

    List<ItemCarrinhoDTO> findAllByClienteId(Long id);
}
