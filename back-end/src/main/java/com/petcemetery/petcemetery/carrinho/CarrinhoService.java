package com.petcemetery.petcemetery.carrinho;

import com.petcemetery.petcemetery.carrinho.dto.ItemCarrinhoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CarrinhoService {

    private final CarrinhoRepository repository;

    public List<ItemCarrinhoDTO> buscarCarrinho(Long idCliente) {
        return this.repository.findAllByClienteId(idCliente);
    }

}
