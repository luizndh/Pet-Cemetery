package com.petcemetery.petcemetery.services;

import com.petcemetery.petcemetery.dto.ItemCarrinhoDTO;
import com.petcemetery.petcemetery.repositorio.CarrinhoRepository;
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
